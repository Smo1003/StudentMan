package vn.edu.hust.studentman.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import vn.edu.hust.studentman.R
import vn.edu.hust.studentman.StudentModel

class StudentAdapter(private val students: List<StudentModel>) : BaseAdapter() {
    private lateinit var parent: ViewGroup

    override fun getCount(): Int = students.size

    override fun getItem(position: Int): StudentModel = students[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        this.parent = parent
        val view = convertView ?: LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item, parent, false)

        val student = getItem(position)

        val tvName = view.findViewById<TextView>(R.id.tv_name)
        tvName.text = student.studentName

        val tvId = view.findViewById<TextView>(R.id.tv_id)
        tvId.text = student.studentId

        return view
    }
}
