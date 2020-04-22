INSERT INTO item_list (id, name, description, visible_on_dashboard) VALUES
(UUID(), 'todo', 'A typical to do list', true);
INSERT INTO tasks (id, name, description, done) VALUES
( UUID(), 'Wyrzuć śmieci', 'najlepiej do kosza', false),
( UUID(), 'Wyprowadź psa', 'albo najpierw go kup', false),
( UUID(), 'Poucz się programowania', 'tylko nie C', true),
( UUID(), 'Pograj na gitarze', 'rock your socks', false),
( UUID(), 'Poczytaj coś miłego na dobranoc', 'cmentasz sfieszont', true);