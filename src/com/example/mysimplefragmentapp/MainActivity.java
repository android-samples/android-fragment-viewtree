package com.example.mysimplefragmentapp;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	private void log(String msg){
		Log.i("LIFE", "activity:" + msg);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		log("onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			log("ft1");
			ft.add(R.id.container, new MyFragment("f1"));
			log("ft2");
			ft.add(R.id.container, new MyFragment("f2"));
			log("ft3");
			ft.commit();
			log("ft4");
		}
		log("/onCreate");
	}

	@Override protected void onPostCreate(Bundle savedInstanceState) { log("onPostCreate"); super.onPostCreate(savedInstanceState); }
	@Override protected void onStart()		{ log("onStart");	super.onStart(); }
	@Override protected void onRestart()	{ log("onRestart");	super.onRestart(); }
	@Override protected void onResume()		{ log("onResume");	super.onResume(); showTree(); }
	@Override protected void onPause()		{ log("onPause");	super.onPause(); }
	@Override protected void onStop()		{ log("onStop");	super.onStop(); }
	@Override protected void onDestroy()	{ log("onDestroy");	super.onDestroy(); }
	@Override public void onAttachFragment(Fragment fragment) { log("onAttachFragment"); super.onAttachFragment(fragment); }

	// ビューの階層をツリー表示してみる
	private void showTree(){
		String s = getTree(null, "");
		TextView text = (TextView)findViewById(R.id.textView1);
		text.setText(s);
	}
	private String getTree(View v, String indent){
		String ret = "";
		if(v == null){
			v = getWindow().getDecorView();
		}
		// 自分自身
		ret += indent + v.getClass().toString() + "\n";
		// 子
		if(v instanceof ViewGroup){
			ViewGroup group = (ViewGroup)v;
			for(int i=0; i<group.getChildCount(); ++i) {
			    View child = group.getChildAt(i);
			    ret += getTree(child, indent + "  ");
			}
		}
		// 結果
		return ret;
	}

	// fragment
	public static class MyFragment extends Fragment {
		String name;
		
		private void log(String msg){
			Log.i("LIFE", this.name + ":" + msg);
		}
		public MyFragment(String name) {
			this.name = name;
			log("construct");
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			log("onCreateView");
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
		@Override public void onAttach(Activity activity)			{ log("onAttach"); super.onAttach(activity); }
		@Override public void onCreate(Bundle savedInstanceState)	{ log("onCreate"); super.onCreate(savedInstanceState); }
		@Override public void onViewCreated(View view, Bundle savedInstanceState) { log("onViewCreated"); super.onViewCreated(view, savedInstanceState); }
		@Override public void onActivityCreated(Bundle savedInstanceState) { log("onActivityCreated"); super.onActivityCreated(savedInstanceState); }
		@Override public void onStart()			{ log("onStart");	super.onStart(); }
		@Override public void onResume()		{ log("onResume");	super.onResume(); }
		@Override public void onPause()			{ log("onPause");	super.onPause(); }
		@Override public void onStop()			{ log("onStop");		super.onStop(); }
		@Override public void onDestroyView()	{ log("onDestroyView");	super.onDestroyView(); }
		@Override public void onDestroy()		{ log("onDestroy");		super.onDestroy(); }
		@Override public void onDetach()		{ log("onDetach");		super.onDetach(); }
		
	}
}
