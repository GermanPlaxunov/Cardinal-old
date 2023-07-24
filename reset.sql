delete from masada.core_stock where id is not null;
delete from masada.last_provided_stock where id is not null;
delete from masada.price_diff_signal where id is not null;
delete from masada.position where id is not null;
update masada.account set balance = 5000, open_position_cnt = 0 where account_id = '9b6afcd3-8126-4ca9-a871-e66f409e1d68';