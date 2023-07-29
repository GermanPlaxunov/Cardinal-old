import matplotlib.pyplot as plt


def draw_double_moving_average(price_data, signals):
    fig = plt.figure()
    ax1 = fig.add_subplot(111, ylabel='price, $')
    price_data['close'].plot(ax=ax1, color='g', lw=.5)
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


def draw_naive_momentum(price_data, signals):
    fig = plt.figure()
    ax1 = fig.add_subplot(111, ylabel='Price, $')
    price_data['close'].plot(ax=ax1, color='g', lw=.5)
    ax1.plot(signals.loc[signals['orders'] == 1.0].index,
             price_data['close'][signals['orders'] == 1],
             '^', markersize=7, color='k')
    ax1.plot(signals.loc[signals['orders'] == -1.0].index,
             price_data['close'][signals['orders'] == -1],
             'v', markersize=7, color='k')
    plt.legend(['Price', 'Buy', 'Sell'])
    plt.title('Naive momentum trading strategy')
    plt.show()