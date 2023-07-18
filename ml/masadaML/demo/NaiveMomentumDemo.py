from masadaML.processor.trading.strategy.NaiveMomentumStrategy import naive_momentum_strategy
from masadaML.database.DbDataprovider import get_price_history
from masadaML.plot.StrategyPlotter import draw_naive_momentum

if __name__ == '__main__':
    price_history = get_price_history('BTC/USD')
    signals = naive_momentum_strategy(financial_data=price_history,
                                      nb_conseq_days=5)
    draw_naive_momentum(price_data=price_history,
                        signals=signals)
