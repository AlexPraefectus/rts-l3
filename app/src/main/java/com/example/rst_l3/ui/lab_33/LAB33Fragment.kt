package com.example.rst_l3.ui.lab_33

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rst_l3.R
import com.google.android.material.snackbar.Snackbar
import java.lang.NumberFormatException

class LAB33Fragment : Fragment() {

    private lateinit var model: Lab33ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model =
            ViewModelProviders.of(this).get(Lab33ViewModel::class.java)
        val root = inflater.inflate(R.layout.lab33_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_33)
        model.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val resText: TextView = root.findViewById(R.id.l33_res_text)
        model.responseText.observe(viewLifecycleOwner, Observer {
            resText.text = it
        })

        // observing strategy to set description
        val strategiesDescription: TextView = root.findViewById(R.id.l33_strategies_description)
        model.strategy.observe(viewLifecycleOwner, Observer {
            strategiesDescription.text = it.description
        })

        // setting changes listener, choose first item in array to show description
        val spinnerStrategy: Spinner = root.findViewById(R.id.l33_spinner_strategy)
        spinnerStrategy.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                val choices = resources.getStringArray(R.array.l33_strategies)
                model.setStrategy(choices[position])
            }
        }
        spinnerStrategy.setSelection(0)

        val spinnerSample: Spinner = root.findViewById(R.id.l33_spinner_sample_size)
        spinnerSample.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                val choices = resources.getStringArray(R.array.l33_sample_size)
                model.setSamples(choices[position].toInt())
            }
        }
        spinnerSample.setSelection(0)

        val spinnerMutations: Spinner = root.findViewById(R.id.l33_spinner_mutations)
        spinnerMutations.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                val choices = resources.getStringArray(R.array.l33_mutations)
                model.setMutations(choices[position].toDouble())
            }
        }
        spinnerMutations.setSelection(0)

        val spinnerBounds: Spinner = root.findViewById(R.id.l33_spinner_bounds)
        spinnerBounds.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                val choices = resources.getStringArray(R.array.l33_bounds)
                model.setBounds(choices[position])
            }
        }
        spinnerBounds.setSelection(0)

        val textY: EditText = root.findViewById(R.id.l33_y_input)
        textY.setText(model.y.value.toString())
        textY.addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    model.setY(s.toString().toInt())
                } catch (e: NumberFormatException) {
                    view?.let {
                        Snackbar.make(it, "Wrong number format", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                }
            }
        })

        val textA: EditText = root.findViewById(R.id.l33_a_input)
        textA.setText(model.a.value.toString())
        textA.addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    model.setA(s.toString().toInt())
                } catch (e: NumberFormatException) {
                    view?.let {
                        Snackbar.make(it, "Wrong number format", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                }
            }
        })

        val textB: EditText = root.findViewById(R.id.l33_b_input)
        textB.setText(model.b.value.toString())
        textB.addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    model.setB(s.toString().toInt())
                } catch (e: NumberFormatException) {
                    view?.let {
                        Snackbar.make(it, "Wrong number format", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                }
            }
        })

        val textC: EditText = root.findViewById(R.id.l33_c_input)
        textC.setText(model.c.value.toString())
        textC.addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    model.setC(s.toString().toInt())
                } catch (e: NumberFormatException) {
                    view?.let {
                        Snackbar.make(it, "Wrong number format", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                }
            }
        })

        val textD: EditText = root.findViewById(R.id.l33_d_input)
        textD.setText(model.d.value.toString())
        textD.addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    model.setD(s.toString().toInt())
                } catch (e: NumberFormatException) {
                    view?.let {
                        Snackbar.make(it, "Wrong number format", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                }
            }
        })

        val button = root.findViewById<Button>(R.id.l33_button_solve)
        button.setOnClickListener { model.calculate() }

        return root
    }
}
