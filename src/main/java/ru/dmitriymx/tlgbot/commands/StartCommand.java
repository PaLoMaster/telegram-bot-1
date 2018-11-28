package ru.dmitriymx.tlgbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class StartCommand extends BotCommand {

    public StartCommand() {
        super("start", "Start bot");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String message = HelpCommand.getHelpMessage(user.getUserName());
        sendMessage(absSender, chat.getId(), message);
    }

    private void sendMessage(AbsSender sender, Long chatId, String message) {
        SendMessage messageObj = new SendMessage();
        messageObj.setChatId(chatId);
        messageObj.enableMarkdown(true);
        messageObj.setText(message);

        try {
            sender.execute(messageObj);
        } catch (TelegramApiException e) {
            log.error("Send message error", e);
        }
    }
}