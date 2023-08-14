package com.example.statesinfo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.caverock.androidsvg.SVG;

public class SVGImageView extends AppCompatImageView {

    private SVG svg;

    public SVGImageView(Context context) {
        super(context);
    }

    public SVGImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSVG(SVG svg) {
        this.svg = svg;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (svg != null) {
            svg.renderToCanvas(canvas);
        }
    }
}
