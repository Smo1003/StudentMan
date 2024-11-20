package vn.edu.hust.studentman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class StudentAdapter(private val students: MutableList<StudentModel>): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
  class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    val imageEdit: ImageView = itemView.findViewById(R.id.image_edit)
    val imageRemove: ImageView = itemView.findViewById(R.id.image_remove)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item,
       parent, false)
    return StudentViewHolder(itemView)
  }

  override fun getItemCount(): Int = students.size

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]

    holder.textStudentName.text = student.studentName
    holder.textStudentId.text = student.studentId

//    Edit feature
    val editDialogView = LayoutInflater.from(holder.itemView.context).inflate(R.layout.dialog_infor, null)

    val etName = editDialogView.findViewById<EditText>(R.id.et_name)
    etName.setText(student.studentName)

    val etId = editDialogView.findViewById<EditText>(R.id.et_id)
    etId.setText(student.studentId)

    val editDialog = AlertDialog.Builder(holder.itemView.context).apply {
      setView(editDialogView)

      setPositiveButton("OK", { _, _ ->
        holder.textStudentName.text = etName.text.toString()
        students[position].studentName = etName.text.toString()

        holder.textStudentId.text = etId.text.toString()
        students[position].studentId = etId.text.toString()
      })

      setNegativeButton("Hủy", {_, _ ->
      })
    }.create()

    holder.imageEdit.setOnClickListener {
      editDialog.show()
    }

//    Remove feature
    val removeDialogView = LayoutInflater.from(holder.itemView.context).inflate(R.layout.dialog_remove, null)
    val removeDialog = AlertDialog.Builder(holder.itemView.context).apply {
      setView(removeDialogView)

      setPositiveButton("OK", {_, _ ->
        students.removeAt(position)
        notifyItemRemoved(position)

        Snackbar.make(holder.itemView, "Đã xóa ${student.studentName}", Snackbar.LENGTH_LONG).show()
      })

      setNegativeButton("Hủy", {_, _ ->
      })
    }

    holder.imageRemove.setOnClickListener {
      removeDialog.show()
    }
  }
}