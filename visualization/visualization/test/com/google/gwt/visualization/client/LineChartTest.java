/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.visualization.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.events.OnMouseOutHandler;
import com.google.gwt.visualization.client.events.OnMouseOverHandler;
import com.google.gwt.visualization.client.visualizations.LineChart;
import com.google.gwt.visualization.client.visualizations.LineChart.Options;

/**
 * Tests for the LineChart class.
 */
public class LineChartTest extends VisualizationTest {
  
  public void testLineChart() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options = Options.create();
        options.setWidth(400);
        options.setHeight(400);
        options.setLineSize(2);
        options.setPointSize(5);
        options.setSmoothLine(true);
        options.setShowCategories(true);
        widget = new LineChart(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
      }});
  }
  
  public void testOnMouseOver() {
    loadApi(new Runnable() {
      public void run() {
        LineChart chart;
        Options options = Options.create();
        chart = new LineChart(createCompanyPerformance(), options);
        chart.addOnMouseOverHandler(new OnMouseOverHandler() {
          @Override
          public void onMouseOverEvent(OnMouseOverEvent event) {
            assertNotNull(event);
            assertEquals(1, event.getRow());
            assertEquals(1, event.getColumn());
            finishTest();
          }
        });
        triggerOnMouseOver(chart.getJso());
      }
    }, false);
  }
  
  public void testOnMouseOut() {
    loadApi(new Runnable() {
      public void run() {
        LineChart chart;
        Options options = Options.create();
        chart = new LineChart(createCompanyPerformance(), options);
        chart.addOnMouseOutHandler(new OnMouseOutHandler() {
          @Override
          public void onMouseOutEvent(OnMouseOutEvent event) {
            assertNotNull(event);
            assertEquals(1, event.getRow());
            assertEquals(1, event.getColumn());
            finishTest();
          }
        });
        triggerOnMouseOut(chart.getJso());
      }
    }, false);
  }  

  @Override
  protected String getVisualizationPackage() {
    return LineChart.PACKAGE;
  }
  
  private native void triggerOnMouseOut(JavaScriptObject jso) /*-{
    $wnd.google.visualization.events.trigger(jso, 'onmouseout', 
      {'row':1, 'column':1});
  }-*/;

  private native void triggerOnMouseOver(JavaScriptObject jso) /*-{
    $wnd.google.visualization.events.trigger(jso, 'onmouseover', 
      {'row':1, 'column':1});
  }-*/;
}
