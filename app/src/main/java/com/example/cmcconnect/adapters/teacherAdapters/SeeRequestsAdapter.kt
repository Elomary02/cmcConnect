package com.example.cmcconnect.adapters.teacherAdapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cmcconnect.R
import com.example.cmcconnect.model.RequestWithStudent

class SeeRequestsAdapter(private val navController: NavController) : RecyclerView.Adapter<SeeRequestsAdapter.SeeRequestsViewHolder>() {
    private var requests: List<RequestWithStudent> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeRequestsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_formateur_requests_item, parent, false)
        return SeeRequestsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SeeRequestsViewHolder, position: Int) {
        val requestWithStudent = requests[position]
        holder.bind(requestWithStudent)
    }

    override fun getItemCount(): Int {
        return requests.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newRequests: List<RequestWithStudent>) {
        requests = newRequests
        notifyDataSetChanged()
    }

    inner class SeeRequestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.student_name)
        private val motif: TextView = itemView.findViewById(R.id.student_reason)
        private val btnReply: Button = itemView.findViewById(R.id.post_reply)

        fun bind(requestWithStudent: RequestWithStudent) {
            val request = requestWithStudent
            val student = request.student

            name.text = student.name
            motif.text = request.motif


            btnReply.setOnClickListener {
                showRequestPopUp()
            }
        }

        fun showRequestPopUp() {
            val inflater = LayoutInflater.from(itemView.context)
            val dialogView = inflater.inflate(R.layout.formateur_request_popup, null)

            val builder = AlertDialog.Builder(itemView.context)
            builder.setView(dialogView)

            val alertDialog = builder.create()

            val widthInDp = 500
            val heightInDp = 220

            val widthInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthInDp.toFloat(), itemView.context.resources.displayMetrics).toInt()
            val heightInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightInDp.toFloat(), itemView.context.resources.displayMetrics).toInt()

            alertDialog.window?.setLayout(widthInPx, heightInPx)
            alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            val exitBtn = dialogView.findViewById<ImageButton>(R.id.exit_button)
            val studentName = dialogView.findViewById<TextView>(R.id.student_name)
            val studentGroup = dialogView.findViewById<TextView>(R.id.group_name)
            val motif = dialogView.findViewById<TextView>(R.id.reason)
            val reply = dialogView.findViewById<EditText>(R.id.note_input)
            val btnSend = dialogView.findViewById<Button>(R.id.post_reply)

            exitBtn.setOnClickListener {
                alertDialog.dismiss()
            }
            btnSend.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
        }
    }
}

