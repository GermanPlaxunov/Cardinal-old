import matplotlib.pyplot as plt


def show_core_stocks(dataframe):
    fig, ax = plt.subplots()
    ax.plot(dataframe[2], dataframe[3])
    ax.grid(True, linestyle='-.')
    ax.tick_params(labelcolor='r', labelsize='medium', width=3)
    plt.show()


def show_apo(dataframe):
    fig, ax = plt.subplots()
    ax.plot(dataframe[1], dataframe[4])
    ax.grid(True, linestyle='-.')
    ax.tick_params(labelcolor='r', labelsize='medium', width=3)
    plt.show()


def show_bband(dataframe):
    fig, ax = plt.subplots()
    ax.plot(dataframe[1], dataframe[3])
    ax.plot(dataframe[1], dataframe[4])
    ax.plot(dataframe[1], dataframe[6])
    ax.grid(True, linestyle='-.')
    ax.tick_params(labelcolor='r', labelsize='medium', width=3)
    plt.show()


def show_ema(dataframe):
    fig, ax = plt.subplots()
    ax.plot(dataframe[1], dataframe[4])
    ax.grid(True, linestyle='-.')
    ax.tick_params(labelcolor='r', labelsize='medium', width=3)
    plt.show()


def show_rsi(dataframe):
    fig, ax = plt.subplots()
    ax.plot(dataframe[1], dataframe[2])
    ax.plot(dataframe[1], dataframe[3])
    ax.plot(dataframe[1], dataframe[4])
    ax.grid(True, linestyle='-.')
    ax.tick_params(labelcolor='r', labelsize='medium', width=3)
    plt.show()


def show_sma(dataframe):
    fig, ax = plt.subplots()
    ax.plot(dataframe[1], dataframe[4])
    ax.grid(True, linestyle='-.')
    ax.tick_params(labelcolor='r', labelsize='medium', width=3)
    plt.show()


def show_std(dataframe):
    fig, ax = plt.subplots()
    ax.plot(dataframe[1], dataframe[4])
    ax.grid(True, linestyle='-.')
    ax.tick_params(labelcolor='r', labelsize='medium', width=3)
    plt.show()
