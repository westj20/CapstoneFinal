import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BarGraphView extends View {

    private List<Integer> barValues;
    private Paint barPaint;
    private Paint textPaint;

    public BarGraphView(Context context) {
        super(context);
        init();
    }

    public BarGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        barValues = new ArrayList<>();
        barPaint = new Paint();
        barPaint.setColor(Color.BLUE);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30);
    }

    public void setBarValues(List<Integer> values) {
        barValues.clear();
        barValues.addAll(values);
        invalidate(); // Request a redraw
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Calculate bar width and spacing
        int barCount = barValues.size();
        float totalBarWidth = getWidth() * 0.8f;
        float barWidth = totalBarWidth / barCount;
        float barSpacing = (getWidth() - totalBarWidth) / (barCount + 1);

        // Draw bars
        for (int i = 0; i < barCount; i++) {
            float left = i * (barWidth + barSpacing) + barSpacing;
            float right = left + barWidth;
            float top = getHeight() - barValues.get(i);
            float bottom = getHeight();
            canvas.drawRect(left, top, right, bottom, barPaint);

            // Draw bar values
            String valueText = String.valueOf(barValues.get(i));
            float textX = left + (barWidth - textPaint.measureText(valueText)) / 2;
            float textY = top - 10; // Adjust the position based on your preference
            canvas.drawText(valueText, textX, textY, textPaint);
        }
    }
}
