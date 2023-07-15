from masadaML.database.PostgresqlConnector import get_connection


def prepare_string_param(param):
    return '\'' + param + '\''


def get_price_history(from_date, to_date, symbol):
    connection = get_connection()
    cursor = connection.cursor()
    query = f'select t.* from masada.stock_entity t ' \
            f'where t.symbol = {prepare_string_param(symbol)} ' \
            f'and t.date between {prepare_string_param(from_date)} ' \
            f'and {prepare_string_param(to_date)}'
    cursor.execute(query)
    result = cursor.fetchall()
    return result
