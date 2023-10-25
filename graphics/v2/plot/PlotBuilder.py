import matplotlib.pyplot as plt

def build_plot(title, x_label, y_label, X_list, Y_list, names):
    # Setting the title and axis names
    plt.xlabel(xlabel=x_label)
    plt.ylabel(ylabel=y_label)
    plt.title(title)
    colors = ["-b", "-r", "-g"]
    for i in range(0, len(names)):
        x = X_list[i]
        y = Y_list[i]
        name = names[i]
        append_plot(x=x, y=y, color=colors[i], label=name)
    plt.show()


def append_plot(x, y, color, label):
    plt.plot(x, y, color, label=label)


