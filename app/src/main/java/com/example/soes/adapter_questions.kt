package com.example.soes

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class adapter_questions(var mCtx: Context, var resource: Int, var items: List<question>)
    : ArrayAdapter<question>(mCtx, resource, items) {

    internal var helper = Questions_DB_Helper(mCtx)

    override fun getView(position: Int, contentView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resource, null)

        val idET: TextView = view.findViewById(R.id.quesionId)
        val question_textET: TextView = view.findViewById(R.id.QuestionText)

        val updateET: ImageButton = view.findViewById(R.id.Update)
        val deletET: ImageButton = view.findViewById(R.id.Delete)
        val infoET: ImageButton = view.findViewById(R.id.info)

        val questionET: question = items[position]

        idET.text = questionET.id
        question_textET.text = questionET.quesion_text

        updateET.setOnClickListener{
            updateInfo(questionET)
        }
        deletET.setOnClickListener{
            delete(questionET)
        }
        infoET.setOnClickListener{
            getInfo(questionET)
        }
        return view
    }
    fun updateInfo(quesionV:question){

        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Update Info")
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.question_update,null)

        val quesion_text: EditText = view.findViewById(R.id.TextOfQuestion_update)
        val option1ET: EditText = view.findViewById(R.id.option1_update)
        val option2ET: EditText = view.findViewById(R.id.option2_update)
        val option3ET: EditText = view.findViewById(R.id.option3_update)
        val option4ET: EditText = view.findViewById(R.id.option4_update)
        val answerET: EditText = view.findViewById(R.id.correct_answer_update)

        quesion_text.setText(quesionV.quesion_text)
        option1ET.setText(quesionV.option1)
        option2ET.setText(quesionV.option2)
        option3ET.setText(quesionV.option3)
        option4ET.setText(quesionV.option4)
        answerET.setText(quesionV.answer)


        builder.setView(view)



        builder.setPositiveButton("UPDATE", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                val isUpdate = helper.updateData(quesionV.id,
                        quesion_text.text.toString().trim(),
                        option1ET.text.toString().trim(),
                        option2ET.text.toString().trim(),
                        option3ET.text.toString().trim(),
                        option4ET.text.toString().trim(),
                        answerET.text.toString().trim())
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
    fun delete(studentV: question){
        helper.deleteData(studentV.id)
    }
    fun getInfo(quesionV: question){
        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Info Student")
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.question_update,null)

        val quesion_text: EditText = view.findViewById(R.id.TextOfQuestion_update)
        val option1ET: EditText = view.findViewById(R.id.option1_update)
        val option2ET: EditText = view.findViewById(R.id.option2_update)
        val option3ET: EditText = view.findViewById(R.id.option3_update)
        val option4ET: EditText = view.findViewById(R.id.option4_update)
        val answerET: EditText = view.findViewById(R.id.correct_answer_update)

        quesion_text.setText(quesionV.quesion_text)
        option1ET.setText(quesionV.option1)
        option2ET.setText(quesionV.option2)
        option3ET.setText(quesionV.option3)
        option4ET.setText(quesionV.option4)
        answerET.setText(quesionV.answer)

        builder.setView(view)

        builder.setNegativeButton("CANCEL",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }
        })

        val alert = builder.create()
        alert.show()
    }


}