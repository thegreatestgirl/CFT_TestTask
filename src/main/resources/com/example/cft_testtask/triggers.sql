create or replace function delete_readers_trigger() returns trigger as $$
begin
    if ((select count(*) from booked_books where readerid = old.id) > 0) then
        delete from booked_books where readerid = old.id;
    end if;
    return old;
end $$
    language 'plpgsql';

create trigger reader_delete
    before delete on readers for each row
execute procedure delete_readers_trigger();

create or replace function delete_books_trigger() returns trigger as $$
begin
    if ((select count(*) from booked_books where bookid = old.id) > 0) then
        delete from booked_books where bookid = old.id;
    end if;
    return old;
end $$
    language 'plpgsql';

create trigger book_delete
    before delete on books for each row
execute procedure delete_books_trigger();

