package com.testing.survey;

import java.util.List;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

@SuppressWarnings("rawtypes")
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

	private List<Student> stList;

	public DataAdapter(List<Student> students) {
		this.stList = students;
	}

	// Create new views
	@Override
	public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		// create a new view
		View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.list_row, null);

		// create ViewHolder

		ViewHolder viewHolder = new ViewHolder(itemLayoutView);

		return viewHolder;
	}

	@SuppressLint("UseValueOf")
	@Override
	public void onBindViewHolder(final ViewHolder viewHolder,final int position) {

		final Student student = stList.get(position);

		viewHolder.tvQuestionNumber.setText(student.getQuestionNumber());

		viewHolder.tvQuestion.setText(student.getQuestion());

		viewHolder.rbAns1.setText(student.getAnswer1());

		viewHolder.rbAns2.setText(student.getAnswer2());

		viewHolder.rbAns3.setText(student.getAnswer3());

		viewHolder.rbAns4.setText(student.getAnswer4());

		viewHolder.rbAns5.setText(student.getAnswer4());

		viewHolder.rgAnswers.clearCheck();

		if (student.getSelectedRadioButtonId() != 0)
			viewHolder.rgAnswers.check(student.getSelectedRadioButtonId());
		else
			viewHolder.rgAnswers.clearCheck();

		viewHolder.rgAnswers
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						if (checkedId != -1) {
							student.setSelectedRadioButtonId(checkedId);
							RadioButton rb = (RadioButton) viewHolder.rgAnswers
									.findViewById(checkedId);

							student.setFinalAnswer(rb.getText().toString());
							stList.add(student);
						}
					}
				});
	}

	// Return the size arraylist
	@Override
	public int getItemCount() {
		return stList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView tvQuestionNumber;
		public TextView tvQuestion;

		public RadioGroup rgAnswers;
		public RadioButton rbAns1, rbAns2, rbAns3, rbAns4, rbAns5;

		public Student singlestudent;

		public ViewHolder(View itemLayoutView) {
			super(itemLayoutView);

			tvQuestionNumber = (TextView) itemLayoutView
					.findViewById(R.id.tvQuestionNumber);

			tvQuestion = (TextView) itemLayoutView
					.findViewById(R.id.tvQuestion);
			rgAnswers = (RadioGroup) itemLayoutView
					.findViewById(R.id.rgAnswers);
			rbAns1 = (RadioButton) itemLayoutView.findViewById(R.id.rbAnswer1);
			rbAns2 = (RadioButton) itemLayoutView.findViewById(R.id.rbAnswer2);
			rbAns3 = (RadioButton) itemLayoutView.findViewById(R.id.rbAnswer3);
			rbAns4 = (RadioButton) itemLayoutView.findViewById(R.id.rbAnswer4);
			rbAns5 = (RadioButton) itemLayoutView.findViewById(R.id.rbAnswer5);

		}

	}

	// method to access in activity after updating selection
	public List<Student> getStudentist() {
		return stList;
	}

}
