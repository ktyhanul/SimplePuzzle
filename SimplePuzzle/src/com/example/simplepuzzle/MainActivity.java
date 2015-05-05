package com.example.simplepuzzle;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	//LinearLayout puzzle1,puzzle2,puzzle3,puzzle4,puzzle5,puzzle6;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("tag","Message1");
		super.onCreate(savedInstanceState);
		Log.i("tag","Message2");
		setContentView(R.layout.activity_main);
		Log.i("tag","Message3");

		findViewById(R.id.imageView1).setOnTouchListener(mTouchListener);
		findViewById(R.id.imageView2).setOnTouchListener(mTouchListener);
		findViewById(R.id.imageView3).setOnTouchListener(mTouchListener);
		findViewById(R.id.imageView4).setOnTouchListener(mTouchListener);
		findViewById(R.id.imageView5).setOnTouchListener(mTouchListener);
		findViewById(R.id.imageView6).setOnTouchListener(mTouchListener);
		
		findViewById(R.id.puzzle_1).setOnDragListener(mDragListener);
		findViewById(R.id.puzzle_2).setOnDragListener(mDragListener);
		findViewById(R.id.puzzle_3).setOnDragListener(mDragListener);
		findViewById(R.id.puzzle_4).setOnDragListener(mDragListener);
		findViewById(R.id.puzzle_5).setOnDragListener(mDragListener);
		findViewById(R.id.puzzle_6).setOnDragListener(mDragListener);

	}


	View.OnTouchListener mTouchListener = new View.OnTouchListener(){
		@Override
		public boolean onTouch(View view, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN){
				ClipData clip = ClipData.newPlainText("", "");
				view.startDrag(clip, new CanvasShadow(view,(int)event.getX(),(int)event.getY()),view,0);
				view.setVisibility(View.INVISIBLE);
				return true;
			}
			return false;
		}		
	};

	View.OnDragListener mDragListener = new View.OnDragListener(){
		public boolean onDrag(View v, DragEvent event){
			switch(event.getAction()){
			case DragEvent.ACTION_DRAG_STARTED:
				return true;
			case DragEvent.ACTION_DRAG_ENTERED:
				return true;
			case DragEvent.ACTION_DRAG_EXITED:
				return true;
			case DragEvent.ACTION_DROP:
				View view = (View)event.getLocalState();
				ViewGroup parent = (ViewGroup)view.getParent();
				LinearLayout newparent = (LinearLayout)v;
				if(matchPuzzle(newparent, view)){
					parent.removeView(view);
					view.setVisibility(View.VISIBLE);
				}
				else{
					((View)(event.getLocalState())).setVisibility(View.VISIBLE);
				}
				return true;
			case DragEvent.ACTION_DRAG_ENDED:
				if (event.getResult() == false){
					((View)(event.getLocalState())).setVisibility(View.VISIBLE);
				}
				return true;
			}

			return true;	
		}


	};

	private boolean matchPuzzle(View match1, View match2){
		if(match1 == findViewById(R.id.puzzle_1)){
			if(match2 == findViewById(R.id.imageView1)){
				match1.setBackgroundResource(R.drawable.puzzle_1);
				return true;
			}
		}
		else if(match1 == findViewById(R.id.puzzle_2)){
			if(match2 == findViewById(R.id.imageView2)){
				match1.setBackgroundResource(R.drawable.puzzle_2);
				return true;
			}
		}
		else if(match1 == findViewById(R.id.puzzle_3)){
			if(match2 == findViewById(R.id.imageView3)){
				match1.setBackgroundResource(R.drawable.puzzle_3);
				return true;
			}
		}
		else if(match1 == findViewById(R.id.puzzle_4)){
			if(match2 == findViewById(R.id.imageView4)){
				match1.setBackgroundResource(R.drawable.puzzle_4);
				return true;
			}
		}
		else if(match1 == findViewById(R.id.puzzle_5)){
			if(match2 == findViewById(R.id.imageView5)){
				match1.setBackgroundResource(R.drawable.puzzle_5);
				return true;
			}
		}
		else if(match1 == findViewById(R.id.puzzle_6)){
			if(match2 == findViewById(R.id.imageView6)){
				match1.setBackgroundResource(R.drawable.puzzle_6);
				return true;
			}
		}
		return false;

	}

}
