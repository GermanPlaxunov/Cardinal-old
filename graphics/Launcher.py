import visualizer.ApoVisualizer as apo
import visualizer.BbandVisualizer as bband
import visualizer.CoreStocksVisualizer as stocks
import visualizer.EmaVisualizer as ema
import visualizer.RsiVisualizer as rsi
import visualizer.SmaVisualizer as sma
import visualizer.StdVisualizer as std

if __name__ == '__main__':
    apo.visualize()
    bband.visualize()
    stocks.visualize()
    ema.visualize()
    rsi.visualize()
    sma.visualize()
    std.visualize()