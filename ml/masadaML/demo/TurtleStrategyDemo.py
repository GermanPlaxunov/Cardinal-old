from masadaML.processor.trading.strategy.TurtleStrategy import turtle_trading
from masadaML.database.DbDataprovider import get_price_history
from masadaML.plot.StrategyPlotter import draw_naive_momentum

if __name__ == '__main__':
    price_data = get_price_history('BTC/USD')
    signals = turtle_trading(financial_data=price_data,
                             window_size=10)
    draw_naive_momentum(price_data=price_data,
                        signals=signals)