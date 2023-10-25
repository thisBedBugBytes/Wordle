import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;

public class barchart extends ApplicationFrame {

    private double win, loss;
    public barchart(String title, String chartTitle, double w, double l) {
        super(title);
        this.win = (w/(w+l))*100;
        this.loss = (l/(w+l))*100;
        double total = this.loss+this.win;
        DefaultCategoryDataset data = new DefaultCategoryDataset();

        data.addValue(this.win, "Wins", "Wins" );
        data.addValue(this.loss, "Loss", "Loss");
        data.addValue(total, "Total", "Total");


        JFreeChart barChart =  ChartFactory.createBarChart(chartTitle,
                "Wordle Stat",
                "%",
                data,
                PlotOrientation.HORIZONTAL,
                true,
                false,
                false);

        ChartPanel panel = new ChartPanel(barChart);
        panel.setPreferredSize(new Dimension(450,400));
        setContentPane(panel);
        pack();
        setVisible(true);

    }
}
