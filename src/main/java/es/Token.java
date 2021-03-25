package es;

import java.util.Objects;

/**
 * @author gang
 * @Description:
 * @date 2020/5/13 18:05
 */
public class Token {
    private Integer pos;
    private String content;

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Token{" +
                "pos=" + pos +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return pos.equals(token.pos) &&
                content.equals(token.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, content);
    }
}
