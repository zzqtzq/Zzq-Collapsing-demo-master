package com.zzq.zzq_collapsing_demo_master.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 压缩图片
 * 
 * @author Administrator
 * 
 */
public class Bimp {
	public static int max = 0;
	public static boolean act_bool = true;
	// 图片集合
	public static List<Bitmap> bmp = new ArrayList<Bitmap>();

	// 图片sd地址 上传服务器时把图片调用下面方法压缩后 保存到临时文件夹 图片压缩后小�?00KB，失真度不明�?
	public static List<String> drr = new ArrayList<String>();

	// TelephonyManager tm = (TelephonyManager) this
	// .getSystemService(Context.TELEPHONY_SERVICE);

	// public static Bitmap revitionImageSize(String path) throws IOException {
	// BufferedInputStream in = new BufferedInputStream(new FileInputStream(
	// new File(path)));
	//
	// BitmapFactory.Options options = new BitmapFactory.Options();
	// options.inJustDecodeBounds = true;
	// // Bitmap btBitmap=BitmapFactory.decodeFile(path);
	// // System.out.println("原尺寸高度："+btBitmap.getHeight());
	// // System.out.println("原尺寸宽度："+btBitmap.getWidth());
	// BitmapFactory.decodeStream(in, null, options);
	// in.close();
	// int i = 0;
	// Bitmap bitmap = null;
	// while (true) {
	// if ((options.outWidth >> i <= 1600)
	// && (options.outHeight >> i <= 1600)) {
	// in = new BufferedInputStream(
	// new FileInputStream(new File(path)));
	// options.inSampleSize = (int) Math.pow(2.0D, i);
	// options.inJustDecodeBounds = false;
	// bitmap = BitmapFactory.decodeStream(in, null, options);
	// break;
	// }
	// i += 1;
	// }
	// // 当机型为三星时图片翻�?
	// // bitmap = Photo.photoAdapter(path, bitmap);
	// // System.out.println("-----压缩后尺寸高度：" + bitmap.getHeight());
	// // System.out.println("-----压缩后尺寸宽度度�? + bitmap.getWidth());
	// return bitmap;
	// }

	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis); // /把流转化为Bitmap图片

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param x
	 *            图像的宽�?
	 * @param y
	 *            图像的高�?
	 * @param image
	 *            源图�?
	 * @param outerRadiusRat
	 *            圆角的大�?
	 * @return 圆角图片
	 */
	public static Bitmap createFramedPhoto(int x, int y, Bitmap image,
			float outerRadiusRat) {
		// 根据源文件新建一个darwable对象
		Drawable imageDrawable = new BitmapDrawable(image);

		// 新建�?��新的输出图片
		Bitmap output = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		// 新建�?��矩形
		RectF outerRect = new RectF(0, 0, x, y);

		// 产生�?��红色的圆角矩�?
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.RED);
		canvas.drawRoundRect(outerRect, outerRadiusRat, outerRadiusRat, paint);

		// 将源图片绘制到这个圆角矩形上
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		imageDrawable.setBounds(0, 0, x, y);
		canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);
		imageDrawable.draw(canvas);
		canvas.restore();

		return output;
	}

	// 网络图片转换方法
	public static Bitmap getUrlImage(String url) {
		Bitmap img = null;
		try {
			URL picurl = new URL(url);
			// 获得连接
			HttpURLConnection conn = (HttpURLConnection) picurl
					.openConnection();
			conn.setConnectTimeout(6000);// 设置超时
			conn.setDoInput(true);
			conn.setUseCaches(false);// 不缓存
			conn.connect();
			InputStream is = conn.getInputStream();// 获得图片的数据流
			img = BitmapFactory.decodeStream(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img;
	}
}
