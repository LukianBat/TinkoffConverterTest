package com.memebattle.tinkoffconvertertest.feature.converter.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.memebattle.tinkoffconvertertest.core.domain.BaseCallback
import com.memebattle.tinkoffconvertertest.R
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import android.widget.Toast
import com.memebattle.tinkoffconvertertest.feature.converter.domain.model.Results

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    var current: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initSpinner()
        viewModel.downloadList(object : BaseCallback<Boolean> {
            override fun onSuccess(result: Boolean) {

            }

            override fun onError(error: Throwable) {
                Toast.makeText(applicationContext, R.string.NetworkError, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun onConvertClick(view: View) {
        if (viewModel.isCorrectCurrent(numberFrom.text.toString())) {
            viewModel.toConvert(spinnerFrom.selectedItem.toString(), spinnerTo.selectedItem.toString(), numberFrom.text.toString().toDouble(), object : BaseCallback<Double> {
                override fun onSuccess(result: Double) {
                    numResult.text = numberFrom.text.toString() + " " + spinnerFrom.selectedItem.toString() + " -> " + result.toString() + " " + spinnerTo.selectedItem.toString()
                }

                override fun onError(error: Throwable) {
                    numResult.setText(R.string.NetworkError)
                }
            })
        } else {
            numResult.setText(R.string.CurrentError)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        numResult.text = savedInstanceState.getString("current").toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        current = numResult.text.toString()
        outState.putString("current", current)
    }

    private fun initSpinner() {
        val fields = ArrayList<String>()
        for (field in Results::class.java.declaredFields) {
            if (field.name.length == 3) {
                fields.add(field.name.toString())
            }
        }
        val adapter = ArrayAdapter<String>(applicationContext, R.layout.spinner_item, fields)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter
    }

}