from masadaML.plot.StrategyPlotter import draw_double_moving_average
from masadaML.database.DbDataprovider import get_price_history
from masadaML.processor.trading.strategy.DoubleMovingAverage import double_moving_average


# This shows the plot of Double Moving Average
if __name__ == '__main__':
    price_history = get_price_history(symbol='BTC/USD')
    signals = double_moving_average(price_history, 20, 100)
    draw_double_moving_average(price_data=price_history, signals=signals)
    
