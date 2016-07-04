package com.zhang.util;

import java.util.List;
import org.easycoding.mobile.android.utils.ui.Item;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhang.interstcoding.R;

public class Alert {
	private static Context parent;
	public static final int EXIT_APPLICATION = 0x0001;
	public static ProgressDialog mProgressDialog;
	public static Dialog dialog;
	public static int shakeFlag = -1;
	public static String floor;
	public static int role;
	public static Boolean jumpFlags = false;// 控制缴费成功后dialog的限制工作
	public static final String screKey = "248b63f1ceca9158ca88516bcb338e82a482ecd802cbca12";// Appid
																							// 微信语音应用id

	/**
	 * 小进度条
	 * 
	 * @param context
	 */
	public static void customDialog(Context context){
		try {
			dialog = new Dialog(context, R.style.myDialog);
			dialog.setContentView(R.layout.progress_dialog);
			dialog.setCancelable(true);// 按返回键不能退出
			dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
			dialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 小进度条，可设置文字信息
	 * 
	 * @param activity
	 * @param message
	 */
	public static void customDialog(Activity activity, String message) {
		try {
			LayoutInflater inflater = activity.getLayoutInflater();
			View dailogView = inflater.inflate(R.layout.progress_dialog, null);
			TextView tvTitle = (TextView) dailogView
					.findViewById(R.id.tv_progress_dialog);
			tvTitle.setText(message);
			dialog = new Dialog(activity, R.style.myDialog);
			dialog.setContentView(dailogView);
			dialog.setCancelable(true);// 按返回键不能退出
			dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
			dialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 大进度条
	@Deprecated
	public static void customDialogBig1(final Context context, String title,
			boolean canCancel) {
		try {
			dialog = new Dialog(context, R.style.myDialog);
			dialog.setContentView(R.layout.progress_dialog_big);
			dialog.setTitle(title); // 设置标题
			dialog.setCancelable(true);// 按返回键不能退出
			dialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog不消失
			if (canCancel) {
				dialog.setOnCancelListener(new OnCancelListener() {

					@Override
					public void onCancel(DialogInterface arg0) {
						dialog.dismiss();
					}
				});
			}
			dialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 取消dialog
	 * 
	 * @param msg
	 * @param mparent
	 * @return
	 */
	public static AlertDialog showMsg(String msg, Context mparent) {
		parent = mparent;
		Builder alert_show = new Builder(parent);
		AlertDialog alert_window;
		alert_window = alert_show
				.setTitle("温馨提示")
				.setMessage(msg)
				.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).show();

		return alert_window;
	}

	// 大进度条
	public static void showDialog(Context context) {
		try {
			parent = context;
			mProgressDialog = new ProgressDialog(context);
			mProgressDialog.setMessage("请耐心等待……");
			mProgressDialog.setCancelable(false);// 按返回键不能退出
			mProgressDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
			mProgressDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * @see 公共方法 非wifi网络状态下的提醒
	 */
	public static Boolean updateConnectedFlags(Activity context) {
		Boolean wifiConnected = false;
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Service.CONNECTIVITY_SERVICE);
		NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
		if (activeInfo != null && activeInfo.isConnected()) {
			wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
		} else {
			wifiConnected = false;
		}
		return wifiConnected;
	}

	public static void itemDialog(Activity activity, String title,
			List<Item> items, DialogInterface.OnClickListener select) {
		String[] strArray = new String[items.size()];
		for (int i = 0; i < items.size(); i++) {
			strArray[i] = items.get(i).getName();
		}
		Dialog dialog = new Builder(activity).setTitle(title)
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).setItems(strArray, select).create();
		dialog.show();
	}

	public static void showLongToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}

	public static void showShortToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	// 对话框标题
	private static final String MESSAGE_DIALOG_TITLE = "提示";

	/**
	 * 显示消息对话框
	 * 
	 * @param context
	 *            对话框上下文
	 * @param title
	 *            标题
	 * @param message
	 *            消息内容
	 */
	public static void showMessageDialog(Context context, String title,
			String message) {
		Builder builder = new Builder(context);
		builder.setCancelable(true).setTitle(title).setMessage(message)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).setOnCancelListener(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						dialog.dismiss();
					}
				}).create().show();
	}

	/**
	 * 显示消息对话框
	 * 
	 * @param context
	 *            对话框上下文
	 * @param message
	 *            消息内容
	 */
	public static void showMessageDialog(Context context, String message) {
		showMessageDialog(context, MESSAGE_DIALOG_TITLE, message);
	}

	/**
	 * 确认对话框按下返回按钮事件 which 参数值
	 */
	public static final int PROMPT_DIALOG_WHICH_BACK = -100;
	/**
	 * 确认对话框“确认”按钮 which 参数值
	 */
	public static final int PROMPT_DIALOG_WHICH_OK = -1;
	/**
	 * 确认对话框“取消”按钮 which 参数值
	 */
	public static final int PROMPT_DIALOG_WHICH_CANCEL = -2;

	/**
	 * 显示确认对话框，对话框包含“确认”和“取消”两个按钮
	 * 
	 * @param context
	 *            对话框上下文
	 * @param title
	 *            标题
	 * @param message
	 *            消息内容
	 * @param okButtonText
	 *            “确认”按钮标题
	 * @param cancelButtonText
	 *            “取消”按钮标题
	 * @param listener
	 *            “确认”和“取消”按钮监听器， which=-1时，“确认”按钮被按下， which=-2时，“取消”按钮被按下，
	 *            which=-100时，“返回”按钮被按下
	 */
	public static void showPromptDialog(Context context, String title,
			String message, String okButtonText, String cancelButtonText,
			final DialogInterface.OnClickListener listener) {
		Builder builder = new Builder(context);
		builder.setCancelable(true).setTitle(title).setMessage(message)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton(okButtonText, listener)
				.setNegativeButton(cancelButtonText, listener)
				.setOnCancelListener(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						if (listener == null)
							dialog.dismiss();
						else
							listener.onClick(dialog, PROMPT_DIALOG_WHICH_BACK);
					}
				}).create().show();
	}

	/**
	 * 显示确认对话框，对话框包含“确认”和“取消”两个按钮
	 * 
	 * @param context
	 *            对话框上下文
	 * @param title
	 *            标题
	 * @param message
	 *            消息内容
	 * @param listener
	 *            “确认”和“取消”按钮监听器， which=-1时，确认按钮被按下， which=-2时，取消按钮被按下，
	 *            which=-100时，返回按钮被按下
	 */
	public static void showPromptDialog(Context context, String title,
			String message, final DialogInterface.OnClickListener listener) {
		showPromptDialog(context, title, message, "确认", "取消", listener);
	}

	/**
	 * 显示确认对话框，对话框包含“确认”和“取消”两个按钮
	 * 
	 * @param context
	 *            对话框上下文
	 * @param message
	 *            消息内容
	 * @param listener
	 *            “确认”和“取消”按钮监听器， which=-1时，确认按钮被按下， which=-2时，取消按钮被按下，
	 *            which=-100时，返回按钮被按下
	 */
	public static void showPromptDialog(Context context, String message,
			final DialogInterface.OnClickListener listener) {
		showPromptDialog(context, MESSAGE_DIALOG_TITLE, message, listener);
	}

}
