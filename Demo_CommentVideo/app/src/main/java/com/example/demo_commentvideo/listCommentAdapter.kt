package com.example.demo_commentvideo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class listCommentAdapter(var listComment: MutableList<User>, val replyListener:ReplyListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(user: User) {
            var listReply: MutableList<User> = mutableListOf()
            itemView.findViewById<ImageView>(R.id.avatar).setImageResource(user.avt)
            itemView.findViewById<TextView>(R.id.username).text = user.name
            itemView.findViewById<TextView>(R.id.commentTime).text = user.timeOfComment
            itemView.findViewById<TextView>(R.id.commentContent).text = user.comment
            if (!user.isTicked) {
                itemView.findViewById<ImageView>(R.id.bluetick)
                    .visibility = View.GONE
            }
            val replyLayout:RelativeLayout = itemView.findViewById(R.id.layout_userReply)
            val cancelRepy:AppCompatButton = itemView.findViewById(R.id.cancelReply_button)
            val sendReply:AppCompatButton = itemView.findViewById(R.id.send_buttonReply)
            val edtUserReply:AppCompatEditText = itemView.findViewById(R.id.edt_userCommentReply)

            replyLayout.visibility = View.GONE

            itemView.findViewById<AppCompatTextView>(R.id.btnReply).setOnClickListener {
                if(replyLayout.isGone){
                    replyLayout.visibility = View.VISIBLE
                    replyListener.userComment(cancelRepy,sendReply,edtUserReply,listReply)
                }else{
                    replyLayout.visibility = View.GONE
                }
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_comment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(listComment[position])
    }

    override fun getItemCount(): Int {
        return listComment.size
    }

    interface ReplyListener{
        fun userComment(cancelButton: AppCompatButton,sendButton: AppCompatButton,editText: AppCompatEditText,listComment: MutableList<User>)
        fun onWriteCommentListener(editText: AppCompatEditText, cancelButton: AppCompatButton, sendButton: AppCompatButton)
        fun onCancelUserComment(cancelButton: AppCompatButton,editText: AppCompatEditText)
        fun onSendUserComment(sendButton: AppCompatButton,listComment:MutableList<User>,editText: AppCompatEditText,cancelButton: AppCompatButton)
        fun clearEdittext(editText: AppCompatEditText,cancelButton: AppCompatButton)
        fun hideKeyboard(view: View)
    }
}