import pandas as pd


def naive_momentum_strategy(financial_data, nb_conseq_days):
    signals = pd.DataFrame(index=financial_data.index)
    signals['orders'] = 0
    cons_day = 0
    prior_price = 0
    init = True
    for k in range(len(financial_data['close'])):
        price = financial_data['close'][k]
        if init:
            prior_price = price
            init = False
        elif price > prior_price:
            if cons_day > 0:
                cons_day = 0
            cons_day -= 1
        if cons_day == nb_conseq_days:
            signals['orders'][k] = 1
        elif cons_day == -nb_conseq_days:
            signals['orders'][k] = -1
    return signals
