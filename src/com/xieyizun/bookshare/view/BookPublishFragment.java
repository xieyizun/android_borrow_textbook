package com.xieyizun.bookshare.view;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.xieyizun.bookshare.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class BookPublishFragment extends Fragment {

	private EditText bookName;
	private EditText bookAuthor;
	private EditText bookBrief;
	private Spinner bookType;
	
	private File imageFile;
	private ImageView image;
	
	private Button takePhoto;
	private Button commit;
	
	private final int TAKE_PHOTO_REQUEST = 1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundles) {
		View rootView = (View)inflater.inflate(R.layout.fragment_bookpublish, container, false);
		
		//设置教材类别
		bookType = (Spinner)rootView.findViewById(R.id.book_type);
		
		List<String> types = new ArrayList<String>();
	
		//根据home中的tabs设置教材的种类数		
		types.add(getString(R.string.title_section1));
		types.add(getString(R.string.title_section2));
		types.add(getString(R.string.title_section3));
		types.add(getString(R.string.title_section4));
		types.add(getString(R.string.title_section5));
		types.add(getString(R.string.title_section6));
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_spinner_item, types);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		bookType.setAdapter(adapter);
		
		//拍照上传封面
		image = (ImageView)rootView.findViewById(R.id.photo);
		takePhoto = (Button)rootView.findViewById(R.id.take_photo);
		takePhoto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				takePhotoIntent();				
			}
		});
		
		//发布图书按钮
		commit = (Button)rootView.findViewById(R.id.commit);
		commit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
							
			}
		});
		return rootView;
	}
	
	@Override
	public void onActivityResult(int request_code, int response_code, Intent data) {
		if (request_code == TAKE_PHOTO_REQUEST && response_code == Activity.RESULT_OK && data != null) {
			Bundle extras = data.getExtras();
    		Bitmap bm = (Bitmap)extras.get("data");
    		image.setImageBitmap(bm);
    	} else {
    		//此处调用这里的方法
    		Bitmap bm = decodeSampleBitmapFromFile(100,100);
    		image.setImageBitmap(bm);
    	}
	}
	//照相
    private void takePhotoIntent() {
    	Context context = getActivity();
    	PackageManager packageManager = context.getPackageManager();
    	if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) == false) {
    		Toast.makeText(context, "未检测到摄像头", Toast.LENGTH_SHORT).show();
    		return;
    	}
    	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    	
    	if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
    		/*//创建图片文件并保存，否则某些手机设备将出现异常
    		String dateStr = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        	String imageFileName = "jpg_" + dateStr + "_";
        	File storeDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);        	
    		
    		try {
    			imageFile = File.createTempFile(imageFileName, ".jpg", storeDir);		
    		} catch (IOException e) {
    			Toast.makeText(context, "拍摄失败", Toast.LENGTH_SHORT).show();
    		}  		
    		
    		if (imageFile != null) {
    			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));*/
    		startActivityForResult(intent, TAKE_PHOTO_REQUEST);
    		//}		
    	}
    }
    
  //计算图片压缩比
    private int calculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
    	//图片的原始大小
    	final int width = options.outWidth;
    	final int height = options.outHeight;
    	
    	int sampleSize = 1;
    	
    	if ((width / sampleSize) > reqWidth || (height / sampleSize) > reqHeight) {
    		final int halfWidth = width/2;
    		final int halfHeight = height/2;
    		
    		while ((halfWidth/sampleSize) > reqWidth && (halfHeight/sampleSize) > reqHeight) {
    			sampleSize *= 2;
    		}
    	}  	

    	return sampleSize;
    }
    //获得压缩图片
    private Bitmap decodeSampleBitmapFromFile( int reqWidth, int reqHeight) {
    	String filename = imageFile.getPath();
    	final BitmapFactory.Options options = new BitmapFactory.Options();
    	//只获取图片的大小类型信息，不加载图片
    	options.inJustDecodeBounds = true;
    	
    	BitmapFactory.decodeFile(filename, options);
    	
    	//计算sampleSize
    	options.inSampleSize = calculateSampleSize(options, reqWidth, reqHeight);
    	//加载缩略图
    	options.inJustDecodeBounds = false;
    	return BitmapFactory.decodeFile(filename, options);
    }
}
