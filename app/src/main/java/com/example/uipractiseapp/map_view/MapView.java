package com.example.uipractiseapp.map_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.graphics.PathParser;

import com.example.uipractiseapp.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MapView extends View {
    private int[] colorArray = new int[]{0xFF239BD7, 0xFF30A9E5, 0xFF80CBF1, 0xFFFFFFFF};
    private Context context;
    private List<ProviceItem> itemList;
    private Paint paint;
    private ProviceItem select;
    private RectF totalRect;
    private float scale=1.0f;

    public MapView(Context context) {
        super(context);
    }

    public MapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setAntiAlias(true);
        itemList= new ArrayList<>();
        loadThread.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //        获取到当前控件宽高值
       int width = MeasureSpec.getSize(widthMeasureSpec);
       int height = MeasureSpec.getSize(heightMeasureSpec);
       if(totalRect!=null){
           double mapWidth = totalRect.width();
           scale = (float) (width/mapWidth);
       }
       setMeasuredDimension(MeasureSpec.makeMeasureSpec(width,MeasureSpec.EXACTLY),
               MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY));
    }

    private Thread loadThread = new Thread(){
        @Override
        public void run() {
          final InputStream inputStream = context.getResources().openRawResource(R.raw.china);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;

            try {
                builder=factory.newDocumentBuilder();
                Document doc = builder.parse(inputStream);
                Element rootElement = doc.getDocumentElement();
                NodeList items = rootElement.getElementsByTagName("path");
                float left = -1;
                float right = -1;
                float top = -1;
                float bottom = -1;
                List<ProviceItem> list = new ArrayList<>();

                for (int i = 0; i <items.getLength(); i++) {
                    Element element = (Element) items.item(i);
                    String pathData = element.getAttribute("android:pathData");
                    Path path = PathParser.createPathFromPathData(pathData);
                    ProviceItem proviceItem = new ProviceItem(path);
                    proviceItem.setDrawColor(colorArray[i%4]);
                    RectF rect =new RectF();
                    path.computeBounds(rect,true);
                    left = left == -1 ? rect.left : Math.min(left, rect.left);
                    right = right == -1 ? rect.right : Math.max(right, rect.right);
                    top = top == -1 ? rect.top : Math.min(top, rect.top);
                    bottom = bottom == -1 ? rect.bottom : Math.max(bottom, rect.bottom);
                    list.add(proviceItem);
                }
                itemList=list;
                totalRect = new RectF(left,top, right, bottom);
                //刷新界面
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //requestlayout会调用parent的requestLayout,这里会调用 checkThread(),所有只能在主线程
                        requestLayout();
                        invalidate();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        handleTouch(event.getX()/scale, event.getY()/scale);
        return super.onTouchEvent(event);
    }

    private void handleTouch(float x, float y) {
        if(itemList==null){
            return;
        }
        ProviceItem selectItem = null;
        for (ProviceItem proviceItem: itemList) {
            if(proviceItem.isTouch(x, y)){
                selectItem = proviceItem;
            }
        }
        if(selectItem!=null){
            select = selectItem;
            postInvalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(itemList!=null){
            canvas.save();
            canvas.scale(scale,scale);
            for(ProviceItem proviceItem:itemList){
                if (proviceItem!=select){
                    proviceItem.drawItem(canvas, paint, false);
                }else{
                    select.drawItem(canvas, paint, true);
                }
            }
        }
    }
}
