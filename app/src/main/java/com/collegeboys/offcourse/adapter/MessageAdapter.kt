package com.collegeboys.offcourse.adapter

import com.collegeboys.offcourse.database.entity.Message

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.lifecycle.LiveData
import com.collegeboys.offcourse.R

class MessageAdapter(
    private val context: Context,
    val ownerId: String,
    val otherUserId: String
) : BaseAdapter() {
    private val messages: MutableList<Message> = mutableListOf()

    fun add(message: Message) {
        messages.add(message)
    }

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup?): View {
        val messageInflater =
            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val message = messages[index]

        val convertView = when (message.senderId) {
            ownerId -> messageInflater.inflate(R.layout.my_message_bubble, null)
            else -> messageInflater.inflate(R.layout.received_message_bubble, null)
        }
        val holder = MessageViewHolder(
            convertView.findViewById(R.id.message_body)
        )
        holder.messageBody.text = message.text
        convertView.tag = holder
        return convertView
    }

    override fun getItem(index: Int): Message = messages[index]

    override fun getItemId(index: Int): Long = index.toLong()

    override fun getCount(): Int = messages.size
}

data class MessageViewHolder(
    val messageBody: TextView,
)
