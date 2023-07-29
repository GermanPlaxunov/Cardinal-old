import pandas as pd


def turtle_trading(financial_data, window_size):
    signals = pd.DataFrame(index=financial_data.index)
    signals['orders'] = 0
    signals['high'] = financial_data['close'].shift(1) \
        .rolling(window=window_size) \
        .max()
    signals['low'] = financial_data['close'].shift(1) \
        .rolling(window=window_size) \
        .min()
    signals['avg'] = financial_data['close'].shift(1) \
        .rolling(window=window_size) \
        .mean()
    signals['long_entry'] = financial_data['close'] > signals['high']
    signals['short_entry'] = financial_data['close'] < signals['low']
    signals['long_exit'] = financial_data['close'] < signals.avg
    signals['short_exit'] = financial_data['close'] > signals.avg

    position = 0
    for k in range(len(signals)):
        if signals['long_entry'][k] and position == 0:
            signals['orders'].values[k] = 1
            position = 1
        elif signals['short_entry'][k] and position == 0:
            signals['orders'][k] = -1
            position = -1
        elif signals['short_exit'][k] and position > 0:
            signals['orders'][k] = -1
            position = 0
        else:
            signals['orders'][k] = 0

    return signals
