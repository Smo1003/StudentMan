package vn.edu.hust.studentman

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import vn.edu.hust.studentman.adapter.StudentAdapter

class MainActivity : AppCompatActivity() {
  private lateinit var lvStudents : ListView

  private lateinit var students : MutableList<StudentModel>
  private lateinit var adapter: StudentAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    students = mutableListOf(
      StudentModel("Nguyễn Văn An", "SV001"),
      StudentModel("Trần Thị Bảo", "SV002"),
      StudentModel("Lê Hoàng Cường", "SV003"),
      StudentModel("Phạm Thị Dung", "SV004"),
      StudentModel("Đỗ Minh Đức", "SV005"),
      StudentModel("Vũ Thị Hoa", "SV006"),
      StudentModel("Hoàng Văn Hải", "SV007"),
      StudentModel("Bùi Thị Hạnh", "SV008"),
      StudentModel("Đinh Văn Hùng", "SV009"),
      StudentModel("Nguyễn Thị Linh", "SV010"),
      StudentModel("Phạm Văn Long", "SV011"),
      StudentModel("Trần Thị Mai", "SV012"),
      StudentModel("Lê Thị Ngọc", "SV013"),
      StudentModel("Vũ Văn Nam", "SV014"),
      StudentModel("Hoàng Thị Phương", "SV015"),
      StudentModel("Đỗ Văn Quân", "SV016"),
      StudentModel("Nguyễn Thị Thu", "SV017"),
      StudentModel("Trần Văn Tài", "SV018"),
      StudentModel("Phạm Thị Tuyết", "SV019"),
      StudentModel("Lê Văn Vũ", "SV020")
    )

    adapter = StudentAdapter(students)

    lvStudents = findViewById(R.id.lv_students)
    lvStudents.adapter = adapter

    registerForContextMenu(lvStudents)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == AppConstant.ACT_ADD_STUDENT && resultCode == Activity.RESULT_OK) {
      val newName = data?.getStringExtra("name")
      val newId = data?.getStringExtra("id")

      if (newName != null && newId != null) {
        students.add(StudentModel(newName, newId))
        adapter.notifyDataSetChanged()
      }
    } else if (requestCode == AppConstant.ACT_EDIT_STUDENT && resultCode == Activity.RESULT_OK) {
      val newName = data?.getStringExtra("name")
      val newId = data?.getStringExtra("id")
      val pos = data?.getIntExtra("pos", -1)

      if (newName != null && newId != null) {
        students[pos!!] = StudentModel(newName, newId)
        adapter.notifyDataSetChanged()
      }
    }
  }

  // Context menu
  override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
    super.onCreateContextMenu(menu, v, menuInfo)
    menuInflater.inflate(R.menu.context_menus, menu)
  }

  override fun onContextItemSelected(item: MenuItem): Boolean {
    val info = item.menuInfo as AdapterView.AdapterContextMenuInfo

    val position = info.position
    val student = students[position]

    when (item.itemId) {
      R.id.menu_edit -> {
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra("name", student.studentName)
        intent.putExtra("id", student.studentId)
        intent.putExtra("pos", position)

        startActivityForResult(intent, AppConstant.ACT_EDIT_STUDENT)
      }
      R.id.menu_delete -> {
        students.removeAt(position)
        adapter.notifyDataSetChanged()
      }
    }

    return super.onContextItemSelected(item)
  }

  // Option menu
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.option_menus, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.menu_add -> {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, AppConstant.ACT_ADD_STUDENT)
      }
    }

    return super.onOptionsItemSelected(item)
  }
}