delete from libra.core_stock where id is not null;
delete from libra.last_provided_stock where id is not null;
delete from libra.price_diff_signal where id is not null;
delete from libra.position where id is not null;
update libra.account set balance = 5000, open_position_cnt = 0, is_active = true where account_id = '9b6afcd3-8126-4ca9-a871-e66f409e1d68';
commit;