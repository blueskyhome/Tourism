package com.example.tourism.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourism.R;
import com.example.tourism.activity.QuestionActivity;
import com.example.tourism.tools.MyApplication;
import com.example.tourism.tools.Question;

import java.util.List;
import android.os.Handler;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private Context mContext;
    private Handler handler;
    private ViewHolder holder;
    private List<Question> mQuestionList;
    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView question;
        private TextView answer;
        private View questionItem;

        public ViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.question_text);
            answer = (TextView) view.findViewById(R.id.answer_text);
            questionItem = (View) view.findViewById(R.id.question_item);
        }
    }

    public QuestionAdapter(List<Question> list, Context context, Handler handler) {
        this.mQuestionList = list;
        this.mContext = context;
        this.handler = handler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.question_item, viewGroup, false);
        holder = new ViewHolder(view);
        holder.questionItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerQuestion(holder);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Question question = mQuestionList.get(i);
        viewHolder.question.setText(question.getQuestion());
        viewHolder.answer.setText(question.getAnswer());
    }

    @Override
    public int getItemCount() {
        return mQuestionList.size();
    }

    private void answerQuestion(ViewHolder holder) {
        int position = holder.getAdapterPosition();
        final Question question = mQuestionList.get(position);
        if (!question.getIsAnswer()) {
            AlertDialog.Builder customizeDialog =
                    new AlertDialog.Builder(mContext);
            final View dialogView = LayoutInflater.from(mContext)
                    .inflate(R.layout.dialog_view, null);
            customizeDialog.setTitle("回答问题");
            customizeDialog.setMessage(question.getQuestion());
            customizeDialog.setView(dialogView);
            customizeDialog.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 获取EditView中的输入内容
                            final EditText edit_text =
                                    (EditText) dialogView.findViewById(R.id.edit_text);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Message message = new Message();
                                    message.what = 1;
                                    handler.sendMessage(message);
                                    question.setAnswer(edit_text.getText().toString());
                                    question.setIsAnswer(true);
                                    question.update(question.getObjectId(), new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if (e == null) {
                                                notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }).start();
                        }
                    });
            customizeDialog.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            customizeDialog.show();
        }
    }
}
