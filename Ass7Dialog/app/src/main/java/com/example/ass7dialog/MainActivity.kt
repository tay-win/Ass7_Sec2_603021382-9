package com.example.ass7dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog_layout.view.*


class MainActivity : AppCompatActivity() {
    val empList = arrayListOf<Employee>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testempData()
        recycler_view.adapter = EmployeeAdapter(this.empList, applicationContext)
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.itemAnimator = DefaultItemAnimator()

    }

    fun addEmp(v : View){

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_dialog_layout,null)
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setView(mDialogView)

        val mAlertDialog = mBuilder.show()


        mDialogView.btnAdd.setOnClickListener{
            var radioGroup: RadioGroup = mDialogView.radioGroup
            var selectedId: Int = radioGroup.checkedRadioButtonId
            var radioButton: RadioButton = mDialogView.findViewById(selectedId)

            empList.add(Employee(mDialogView.edit_username.text.toString(),radioButton.text.toString(),mDialogView.edit_email.text.toString(),
                mDialogView.edit_salary.text.toString().toInt()))

            recycler_view.adapter?.notifyDataSetChanged()
            Toast.makeText(applicationContext,"The Employee is added successfully",Toast.LENGTH_SHORT).show()
            mAlertDialog.dismiss()
        }
        mDialogView.btnCancel.setOnClickListener(){
            mAlertDialog.dismiss()
        }
    }
    fun testempData(){
        empList.add(Employee("link","male","link@hotmail.com",200000))
    }
}
