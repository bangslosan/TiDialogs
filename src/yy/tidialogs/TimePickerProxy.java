package yy.tidialogs;

import java.util.Calendar;
import java.util.Date;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.util.TiUIHelper;
import org.appcelerator.titanium.view.TiUIView;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.widget.TimePicker;

@Kroll.proxy(creatableInModule = TidialogsModule.class)
public class TimePickerProxy extends TiViewProxy {
	private class BasicDatePicker extends TiUIView {
		private int clicked;

		private int hour;
		private int minute;

		private String okButtonTitle;
		private String cancelButtonTitle;

		public BasicDatePicker(TiViewProxy proxy) {
			super(proxy);

		}

		private TimePickerDialog getDialog() {
			TimePickerDialog picker = new TimePickerDialog(this.proxy.getActivity(),
						new TimePickerDialog.OnTimeSetListener() {

							@Override
							public void onTimeSet(TimePicker selectedTime,
									int selectedHour, int selectedMinute) {
								// TODO Auto-generated method stub

								hour = selectedHour;
								minute = selectedMinute;

								KrollDict data = new KrollDict();

								if (clicked == DialogInterface.BUTTON_NEGATIVE) {
									data.put("value", null);
									data.put("hour", null);
									data.put("minute", null);
									data.put("cancel", true);
								} else {
									Calendar calendar = Calendar.getInstance();
									calendar.set(Calendar.HOUR_OF_DAY, hour);
									calendar.set(Calendar.MINUTE, minute);
									Date value = calendar.getTime();

									data.put("value", value);
									data.put("hour", hour);
									data.put("minute", minute);
									data.put("cancel", false);
								}

								fireEvent("click", data);
							}
						}, hour, minute, DateFormat.is24HourFormat(this.proxy
								.getActivity()));
			picker.setCanceledOnTouchOutside(false);

			picker.setButton(DialogInterface.BUTTON_NEGATIVE, cancelButtonTitle,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						clicked = which;
					}
				});

			return picker;
		}

		@Override
		public void processProperties(KrollDict d) {
			super.processProperties(d);

			Calendar c = Calendar.getInstance();
			if (d.containsKey("value")) {
				c.setTime((Date) d.get("value"));
				hour = c.get(Calendar.HOUR_OF_DAY);
				minute = c.get(Calendar.MINUTE);
			} else {
				if (d.containsKey("hour")) {
					hour = d.getInt("hour");
				} else {
					hour = c.get(Calendar.HOUR_OF_DAY);
				}
				if (d.containsKey("minute")) {
					minute = d.getInt("minute");
				} else {
					minute = c.get(Calendar.MINUTE);
				}
			}

			if (d.containsKey("okButtonTitle")) {
				okButtonTitle = d.getString("okButtonTitle");
			} else {
				okButtonTitle = "Done";
			}
			if (d.containsKey("cancelButtonTitle")) {
				cancelButtonTitle = d.getString("cancelButtonTitle");
			} else {
				cancelButtonTitle = "Cancel";
			}
		}

		public void show() {
			getDialog().show();
		}

	}

	public TimePickerProxy() {
		super();
	}

	@Override
	public TiUIView createView(Activity activity) {
		return new BasicDatePicker(this);
	}

	@Override
	public void handleCreationDict(KrollDict options) {
		super.handleCreationDict(options);
	}

	@Override
	protected void handleShow(KrollDict options) {
		super.handleShow(options);
		TiUIHelper.runUiDelayedIfBlock(new Runnable() {
			@Override
			public void run() {
				BasicDatePicker d = (BasicDatePicker) getOrCreateView();
				d.show();
			}
		});
	}
}