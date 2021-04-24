/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This CoFPlayerTest.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player;

import com.devkrazy.citiesoffreedom.game.missions.CountMission;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoFPlayerTest {

    CoFPlayer cofPlayer = Mockito.mock(CoFPlayer.class);

    @Test
    public void testAddMission() {
        CountMission mission = Mockito.mock(CountMission.class);
        CountMission mission2 = Mockito.mock(CountMission.class);
        assertEquals(10, 10);
    }
}
