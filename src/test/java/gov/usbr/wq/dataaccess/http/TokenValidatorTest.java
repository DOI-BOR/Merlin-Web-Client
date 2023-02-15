package gov.usbr.wq.dataaccess.http;

import gov.usbr.wq.dataaccess.jwt.TokenContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class TokenValidatorTest
{
    @Test
    void testIsTokenExpired()
    {
        TokenContainer token = new JwtContainer("bad_token_placeholder");
        assertTrue(token.isExpired());
    }

    @Test
    void testIsTokenNotExpired()
    {
        TokenContainer token = new JwtContainer("good_token_placeholder");
        assertFalse(token.isExpired());
    }
}
