package com.example.soes

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class adapter_exams (var mCtx: Context, var resource: Int, var items: List<exam>)
    : ArrayAdapter<exam>(mCtx, resource, items) {

    internal var helper = Exams_DB_Helper(mCtx)

    override fun getView(position: Int, contentView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resource, null)

        val idET: TextView = view.findViewById(R.id.quesionId)
        val Subject_nameET: TextView = view.findViewById(R.id.QuestionText)

        val updateET: ImageButton = view.findViewById(R.id.Update)
        val deletET: ImageButton = view.findViewById(R.id.Delete)
        val infoET: ImageButton = view.findViewById(R.id.info)

        val ExamET: exam = items[position]

        idET.text = ExamET.id
        Subject_nameET.text = ExamET.Subject_name

        updateET.setOnClickListener{
            updateInfo(ExamET)
        }
        deletET.setOnClickListener{
            delete(ExamET)
        }
        infoET.setOnClickListener{
            getInfo(ExamET)
        }
        return view
    }
    fun updateInfo(examV:exam){

        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Update Info")
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.exam_update,null)

        val subject_nameET: EditText = view.findViewById(R.id.subject_update)
        val typeET: EditText = view.findViewById(R.id.type_update)
        val dateET: EditText = view.findViewById(R.id.date_update)
        val fromET: EditText = view.findViewById(R.id.from_update)
        val toET: EditText = view.findViewById(R.id.to_update)

        subject_nameET.setText(examV.Subject_name)
        typeET.setText(examV.Type)
        dateET.setText(examV.Date)
        fromET.setText(examV.From)
        toET.setText(examV.To)


        builder.setView(view)



        builder.setPositiveButton("UPDATE", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                val isUpdate = helper.updateData(examV.id,
                        subject_nameET.text.toString().trim(),
                        typeET.text.toString().trim(),
                        dateET.text.toString().trim(),
                        fromET.text.toString().trim(),
                        toET.text.toString().trim())
                if(isUpdate==true) {
                    Toast.makeText(mCtx,"Updated", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(mCtx,"Update failed", Toast.LENGTH_LONG).show()

                }

            }
        })

        builder.setNegativeButton("CANCEL",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }
        })

        val alert = builder.create()
        alert.show()

    }
    fun delete(examV: exam){
        helper.deleteData(examV.id)
    }
    fun getInfo(examV: exam){
        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Exam info")
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.exam_update,null)

        val subject_nameET: EditText = view.findViewById(R.id.subject_update)
        val typeET: EditText = view.findViewById(R.id.type_update)
        val dateET: EditText = view.findViewById(R.id.date_update)
        val fromET: EditText = view.findViewById(R.id.from_update)
        val toET: EditText = view.findViewById(R.id.to_update)

        subject_nameET.setText(examV.Subject_name)
        typeET.setText(examV.Type)
        dateET.setText(examV.Date)
        fromET.setText(examV.From)
        toET.setText(examV.To)

        builder.setView(view)

        builder.setNegativeButton("CANCEL",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }
        })

        val alert = builder.create()
        alert.show()
    }


}