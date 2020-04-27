package com.example.myprodWeb;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class StarSearchActivity extends Activity {
	EditText edittext_name; //输入姓名的EditText控件
	DatePicker datepicker_bir; //选择日期的控件
	Button btn_search; //查询按钮控件
	ImageView imageview_star; //星座图片控件
	TextView textview_info; //星座信息控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starsearch);
        edittext_name = (EditText)this.findViewById(R.id.editTextName);
		datepicker_bir = (DatePicker)this.findViewById(R.id.datePickerBir);
		btn_search = (Button)this.findViewById(R.id.buttonSearch);
		imageview_star = (ImageView)this.findViewById(R.id.imageViewStar);
		textview_info = (TextView)this.findViewById(R.id.textViewInfo);
		btn_search.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
		// TODO Auto-generated method stub
		int month = datepicker_bir.getMonth();
		//获取当前选择的月份
		int day = datepicker_bir.getDayOfMonth();
		//获取当前选择的日
		int index = searchStar(month, day);
		//调用searchStar根据月日获取响应的星座索引（0～11：水瓶座～摩羯座）
		int[] infoarray = {R.string.aquarius, R.string.pisces,
		R.string.aries, R.string.taurus,
		R.string.gemini,
		R.string.cancer, R.string.leo, R.string.virgo,
		R.string.libra,
		R.string.scorpio, R.string.sagittarius, R.string.capricornus};
		int[] imgarray = {R.drawable.aquarius, R.drawable.pisces,
		R.drawable.aries, R.drawable.taurus,
		R.drawable.gemini,
		R.drawable.cancer, R.drawable.leo, R.drawable.virgo,
		R.drawable.libra,
		R.drawable.scorpio, R.drawable.sagittarius, R.drawable.capricornus};
		String star = StarSearchActivity.this.getString(infoarray[index]); //根据索引获取星座信息字符串
		textview_info.setText(edittext_name.getText().toString()+"，你的星座信息如下：\r\n"+star); //设定星座信息控件
		imageview_star.setImageResource(imgarray[index]);
		//根据索引设定星座图片
		}
		});
    }

  //根据月日获取所在星座的索引，0-11分布代表十二个星座，-1代表参数异常
  	public int searchStar(int month, int day)
  	{
  	int[] DayArr = {20,19,21,21,21,22,23,23,23,23,22,22}; // 两个星座分割日

  	int index = month;
  	// 所查询日期在分割日之前，索引减一，否则不变
  	if (day < DayArr[month])
  	{
  	index = index - 1;
  	if( index < 0 )
  	{
  		index = 11;
  	}
  	}
  	return index;
  	}
  	
}