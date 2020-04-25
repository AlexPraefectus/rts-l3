package com.example.rst_l3.ui.lab_32

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rst_l3.R
import com.example.rst_l3.lib.Dot

class LAB32Fragment : Fragment() {

    private lateinit var model: LAB32ViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProviders.of(this).get(LAB32ViewModel::class.java)
        val root = inflater.inflate(R.layout.lab32_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        model.text.observe(viewLifecycleOwner, Observer { textView.text = it })

        model.dots.observe(viewLifecycleOwner, Observer {
            val header = root.findViewById<TableRow>(R.id.l32_scroll_table_header)
            val table = root.findViewById<TableLayout>(R.id.l32_scroll_table)
            table.removeAllViewsInLayout()
            table.addView(header)

            for (dot: Dot in model.dots.value!!){
                val row = TableRow(context)
                row.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)

                val tv = TextView(context)
                tv.text = dot.toString()
                tv.textSize = 16.0f
                row.addView(tv)

                val s = Switch(context)
                s.gravity = Gravity.END
                s.text = ""
                row.addView(s)
                table.addView(this.context?.let { dot.toTableRow(it) })
            }
        })

        val modeSelector = root.findViewById<Switch>(R.id.l32_mode_switch)
        modeSelector.setOnCheckedChangeListener { _, isChecked ->
            model.setUseIterations(isChecked)
        }

        val speedSelector = root.findViewById<Spinner>(R.id.l32_spinner_learning)
        speedSelector.setSelection(0)
        speedSelector.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                val choices = resources.getStringArray(R.array.l32_learning_choices)
                model.setLearningSpeed(choices[position].toDouble())
            }
        }

        val timeSelector = root.findViewById<Spinner>(R.id.l32_spinner_time)
        timeSelector.setSelection(0)
        timeSelector.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                val choices = resources.getStringArray(R.array.l32_time_choices)
                model.setDeadline(choices[position].toDouble())
            }
        }

        val iterSelector = root.findViewById<Spinner>(R.id.l32_spinner_iter)
        iterSelector.setSelection(0)
        iterSelector.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                val choices = resources.getStringArray(R.array.l32_iter_choices)
                model.setIterations(choices[position].toInt())
            }
        }

        val button: Button = root.findViewById(R.id.l32_btn_learn)
        button.setOnClickListener {
            val table = root.findViewById<TableLayout>(R.id.l32_scroll_table)
            val dotGts = ArrayList<Boolean>()
            for (i in 1 until table.childCount){
                val row: TableRow = table.getChildAt(i) as TableRow
                val s: Switch = row.getChildAt(1) as Switch
                dotGts.add(s.isChecked)
            }
            model.train(dotGts)
        }

        val resView: TextView = root.findViewById(R.id.l32_results_txt)
        model.resText.observe(this, Observer { newResText -> resView.text = newResText })
    return root
    }
}
