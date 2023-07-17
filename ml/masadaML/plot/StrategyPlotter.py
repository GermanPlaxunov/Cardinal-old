import matplotlib.pyplot as plt
from masadaML.processor.trading.strategy.DoubleMovingAverage import double_moving_average


def draw_strategy(price_data):
    fig = plt.figure()
    ax1 = fig.add_subplot(111, ylabel='price, $')
    price_data['close'].plot(ax=ax1, color='g', lw=.5)
    signals = double_moving_average(price_data, 20, 100)
    signals['short_mavg'].plot(ax=ax1, color='r', lw=2.)
    signals['long_mavg'].plot(ax=ax1, color='b', lw=2.)

    ax1.plot(signals.loc[signals['orders'] == 1.0].index,
             price_data['close'][signals['orders'] == 1.0], '^',
             markersize=7,
             color='k')

    ax1.plot(signals.loc[signals['orders'] == -1.0].index,
             price_data['close'][signals['orders'] == -1.0], 'v',
             markersize=7,
             color='k')
    plt.legend(['Price', 'Short mavg', 'Long mavg', 'Buy', 'Sell'])
    plt.title('Double moving average trading strategy')
    plt.show()