package com.collegeboys.offcourse

import com.collegeboys.offcourse.database.entity.Message

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MessageAdapter(private val context: Context, private val userId: String) : BaseAdapter() {
    private val messages: List<Message> = mutableListOf()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val messageInflater =
            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val message = messages.get(p0)

        var convertView = when (message.senderId) {
            userId -> messageInflater.inflate(R.layout.my_message_bubble, null)
            else -> messageInflater.inflate(R.layout.received_message_bubble, null)
        }
        val holder = MessageViewHolder(
            convertView.findViewById(R.id.message_body)
        )
        holder.messageBody.text = message.text
        convertView.tag = holder
        return convertView
    }

    override fun getItem(p0: Int): Message = messages[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getCount(): Int = messages.size
}

data class MessageViewHolder(
    val messageBody: TextView,
)
