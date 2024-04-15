package com.example.littlepaws;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.littlepaws.HomeFragment;


public class SettingsFragment extends Fragment {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String ACTION_BAR_COLOR_KEY = "action_bar_color";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Get the Spinner
        Spinner colorSpinner = view.findViewById(R.id.colorSpinner);

        // Create an ArrayAdapter for the Spinner with color options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.color_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        // Retrieve the saved color from SharedPreferences
        SharedPreferences settings = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int actionBarColor = settings.getInt(ACTION_BAR_COLOR_KEY, R.color.lavender);

        // Set the selected item in the Spinner based on the saved color
        int position = getPositionForColor(actionBarColor);
        colorSpinner.setSelection(position);

        // Set a listener to handle color selection
        colorSpinner.setOnItemSelectedListener(new ColorSelectionListener());

        return view;
    }

    private int getPositionForColor(int color) {
        if (color == R.color.black) {
            return 0;
        } else if (color == R.color.white) {
            return 1;
        } else if (color == R.color.lavender) {
            return 2;
        } else if (color == R.color.purple_200) {
            return 3;
        }
        else if (color==R.color.orange) {
            return 8;
        }
        else if (color==R.color.yellow) {
            return 4;
        }
        else if (color==R.color.brown) {
            return 7;
        }
        else if (color==R.color.pink) {
            return 5;
        } else if (color==R.color.dark_pink) {
            return 6;
        }

        return 2; // Default to "Lavender" if the color doesn't match any option
    }

    private class ColorSelectionListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedColor = parent.getItemAtPosition(position).toString();

            int actionBarColor = R.color.lavender; // Default color
            if (selectedColor.equals("Black")) {
                actionBarColor = R.color.black;
            } else if (selectedColor.equals("White")) {
                actionBarColor = R.color.white;
            } else if (selectedColor.equals("Lavender")) {
                actionBarColor = R.color.lavender;
            } else if (selectedColor.equals("purple_200")) {
                actionBarColor=R.color.purple_200;
            }else if (selectedColor.equals("orange")) {
                actionBarColor=R.color.orange;
            }else if (selectedColor.equals("yellow")) {
                actionBarColor=R.color.yellow;
            }else if (selectedColor.equals("brown")) {
                actionBarColor=R.color.brown;
            }else if (selectedColor.equals("pink")) {
                actionBarColor=R.color.pink;
            } else if (selectedColor.equals("dark_pink")) {
                actionBarColor=R.color.dark_pink;
            }

            // Save the selected color in SharedPreferences
            SharedPreferences settings = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt(ACTION_BAR_COLOR_KEY, actionBarColor);
            editor.apply();

            // Update the action bar color in both fragments
            updateActionBarColor(actionBarColor);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing
        }
    }

    private void updateActionBarColor(int actionBarColor) {
        // Update the action bar color in the HomeFragment
        if (getActivity() != null) {
            HomeFragment homeFragment = (HomeFragment) getActivity().getSupportFragmentManager().findFragmentByTag("homeFragment");
            if (homeFragment != null) {
                homeFragment.changeActionBarColor(actionBarColor);
            }
        }

        // Update the action bar color in the SettingsFragment
        changeActionBarColor(actionBarColor);
    }

    public void changeActionBarColor(int actionBarColor) {
        // Get the support action bar
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();

        // Change the action bar color to the selected color
        actionBar.setBackgroundDrawable(getResources().getDrawable(actionBarColor));
    }
}