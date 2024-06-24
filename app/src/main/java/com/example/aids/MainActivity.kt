package com.example.aids

import android.content.res.AssetManager
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel


class MainActivity : AppCompatActivity() {
    private lateinit var interpreter: Interpreter
    private val mModel = "aids.tflite"

    private lateinit var resultTextView: TextView
    private lateinit var time: EditText
    private lateinit var trt: EditText
    private lateinit var age: EditText
    private lateinit var wtkg: EditText
    private lateinit var homo: EditText
    private lateinit var race: EditText
    private lateinit var gender: EditText
    private lateinit var strat: EditText
    private lateinit var infected: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        resultTextView = findViewById(R.id.txtResult)
        time = findViewById(R.id.time)
        trt = findViewById(R.id.trt)
        age = findViewById(R.id.age)
        wtkg = findViewById(R.id.wtkg)
        homo = findViewById(R.id.homo)
        race = findViewById(R.id.race)
        gender = findViewById(R.id.gender)
        strat = findViewById(R.id.strat)
        infected = findViewById(R.id.infected)

        findViewById<Button>(R.id.btnCheck).setOnClickListener {
            val result = doInterference(
                time.text.toString(),
                trt.text.toString(),
                age.text.toString(),
                wtkg.text.toString(),
                homo.text.toString(),
                race.text.toString(),
                gender.text.toString(),
                strat.text.toString(),
                infected.text.toString()
            )
            resultTextView.text = result
        }
        initInterpreter()
    }

    private fun initInterpreter() {
        val option = Interpreter.Options()
        option.setNumThreads(8)
        option.setUseNNAPI(true)
        interpreter = Interpreter(loadModelFile(assets, mModel), option)
    }

    private fun doInterference(
        input1: String,
        input2: String,
        input3: String,
        input4: String,
        input5: String,
        input6: String,
        input7: String,
        input8: String,
        input9: String
    ): String {
        val inputVal = FloatArray(9)
        inputVal[0] = input1.toFloatOrNull() ?: 0f
        inputVal[1] = input2.toFloatOrNull() ?: 0f
        inputVal[2] = input3.toFloatOrNull() ?: 0f
        inputVal[3] = input4.toFloatOrNull() ?: 0f
        inputVal[4] = input5.toFloatOrNull() ?: 0f
        inputVal[5] = input6.toFloatOrNull() ?: 0f
        inputVal[6] = input7.toFloatOrNull() ?: 0f
        inputVal[7] = input8.toFloatOrNull() ?: 0f
        inputVal[8] = input9.toFloatOrNull() ?: 0f

        val output = Array(1) { FloatArray(2) }
        interpreter.run(inputVal, output)


        val result = output[0]
        val positiveProbability = result[0]
        val negativeProbability = result[1]

        val outputText = if (positiveProbability > negativeProbability) {
            "Positif terkena"
        } else {
            "Negatif terkena"
        }

        val percentage = (positiveProbability * 100).toInt()
        return "$outputText dengan probabilitas $percentage%"
    }

    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }
}

