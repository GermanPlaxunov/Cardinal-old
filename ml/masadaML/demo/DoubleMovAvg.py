from masadaML.plot.StrategyPlotter import draw_strategy
from masadaML.database.DbDataprovider import get_price_history


# This shows the plot of Double Moving Average
if __name__ == '__main__':
    price_history = get_price_history(symbol='BTC/USD')
    draw_strategy(price_data=price_history)
