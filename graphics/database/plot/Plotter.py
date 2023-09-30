import matplotlib.pyplot as plt

def show(dataframe):
    fig, ax = plt.subplots()
    ax.plot(dataframe[2], dataframe[3])
    ax.grid(True, linestyle='-.')
    ax.tick_params(labelcolor='r', labelsize='medium', width=3)
    plt.show()