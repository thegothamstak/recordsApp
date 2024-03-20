package com.stak.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stak.recordkeeper.databinding.FragmentCyclingBinding
import com.stak.recordkeeper.editrecord.EditRecordActivity
import com.stak.recordkeeper.editrecord.INTENT_EXTRA_SCREEN_DATA

class CyclingFragment: Fragment() {
    private lateinit var binding: FragmentCyclingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCyclingBinding.inflate(inflater, container, false)
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

    private fun setUpClickListeners() {
        binding.containerLongestRide.setOnClickListener { launchEditRecordScreen(RECORD_LONGEST_RIDE, "Distance") }
        binding.containerBiggestClimb.setOnClickListener { launchEditRecordScreen(RECORD_BIGGEST_CLIMB, "Height") }
        binding.containerAverageSpeed.setOnClickListener { launchEditRecordScreen(RECORD_AVERAGE_SPEED, "Average Speed") }
    }

    private fun displayRecords() {
        val cyclingPreferences = requireContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE)

        binding.textViewLongestRideValue.text = cyclingPreferences.getString("$RECORD_LONGEST_RIDE ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewLongestRideDate.text = cyclingPreferences.getString("$RECORD_LONGEST_RIDE ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewBiggestClimbValue.text = cyclingPreferences.getString("$RECORD_BIGGEST_CLIMB ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewBiggestClimbDate.text = cyclingPreferences.getString("$RECORD_BIGGEST_CLIMB ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewAverageSpeedValue.text = cyclingPreferences.getString("$RECORD_AVERAGE_SPEED ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewAverageSpeedDate.text = cyclingPreferences.getString("$RECORD_AVERAGE_SPEED ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
    }

    private fun launchEditRecordScreen(record: String, recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(INTENT_EXTRA_SCREEN_DATA, EditRecordActivity.ScreenData(record, FILENAME, recordFieldHint))
        startActivity(intent)
    }

    companion object {
        const val FILENAME = "cycling"
        const val RECORD_LONGEST_RIDE = "Longest Ride"
        const val RECORD_BIGGEST_CLIMB = "Biggest Climb"
        const val RECORD_AVERAGE_SPEED = "Best Avg Speed"
    }
}