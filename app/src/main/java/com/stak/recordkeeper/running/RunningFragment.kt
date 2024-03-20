package com.stak.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stak.recordkeeper.databinding.FragmentRunningBinding
import com.stak.recordkeeper.editrecord.EditRecordActivity
import com.stak.recordkeeper.editrecord.INTENT_EXTRA_SCREEN_DATA

class RunningFragment: Fragment() {
    private lateinit var binding: FragmentRunningBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClickListeners()
    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun setUpClickListeners () {
        binding.container5km.setOnClickListener { launchEditRecordScreen(RECORD_5KM) }
        binding.container10km.setOnClickListener { launchEditRecordScreen(RECORD_10KM) }
        binding.containerHalfMarathon.setOnClickListener { launchEditRecordScreen(RECORD_HALF_MARATHON) }
        binding.containerFullMarathon.setOnClickListener { launchEditRecordScreen(RECORD_FULL_MARATHON) }
    }

    private fun displayRecords() {
        val runningPreferences = requireContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE)

        binding.textView5kmValue.text = runningPreferences.getString("$RECORD_5KM ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textView5kmDate.text = runningPreferences.getString("$RECORD_5KM ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textView10kmValue.text = runningPreferences.getString("$RECORD_10KM ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textView10kmDate.text = runningPreferences.getString("$RECORD_10KM ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewHalfMarathonValue.text = runningPreferences.getString("$RECORD_HALF_MARATHON ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewHalfMarathonDate.text = runningPreferences.getString("$RECORD_HALF_MARATHON ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewFullMarathonValue.text = runningPreferences.getString("$RECORD_FULL_MARATHON ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewFullMarathonDate.text = runningPreferences.getString("$RECORD_FULL_MARATHON ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
    }

    private fun launchEditRecordScreen(distance: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(INTENT_EXTRA_SCREEN_DATA, EditRecordActivity.ScreenData(distance, FILENAME, "Time"))
        startActivity(intent)
    }

    companion object {
        const val FILENAME = "running"
        const val RECORD_5KM = "5km"
        const val RECORD_10KM = "10km"
        const val RECORD_HALF_MARATHON = "Half Marathon"
        const val RECORD_FULL_MARATHON = "Full Marathon"
    }
}