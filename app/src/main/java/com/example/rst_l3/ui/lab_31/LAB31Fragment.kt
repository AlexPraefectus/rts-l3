package com.example.rst_l3.ui.lab_31

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rst_l3.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.lab31_fragment.*

class LAB31Fragment : Fragment() {

    private lateinit var model: LAB31ViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProviders.of(this).get(LAB31ViewModel::class.java)
        val root = inflater.inflate(R.layout.lab31_fragment, container, false)

        val textView: TextView = root.findViewById(R.id.text_gallery)
        model.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val button: Button = root.findViewById(R.id.l31_factorise_btn)
        button.setOnClickListener {view ->
            val errorText = model.setFactorisable(l31_num_input.text.toString());
            if (errorText != null){
                Snackbar.make(view, errorText, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }else{
                model.factorise()
            }
        }

        val resView: TextView = root.findViewById(R.id.l31_result)
        model.getFactorisationResText().observe(this,
            Observer { newResText -> resView.text = newResText })

        return root
    }
}
