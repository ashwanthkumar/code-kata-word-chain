package codekata.wordchain;

import codekata.wordchain.GraphConnections;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

public class GraphConnectionsTest {
    @Test
    public void shouldReturnTrueIfConnected() {
        GraphConnections<String> connections = new GraphConnections<String>()
                .addConnection("north", "south")
                .addConnection("east", "west")
                .addConnection("left", "right");

        assertThat(connections.isConnected("north", "south"), is(true));
        assertThat(connections.isConnected("east", "west"), is(true));
        assertThat(connections.isConnected("left", "right"), is(true));
    }

    @Test
    public void shouldReturnFalseIfNotConnected() {
        GraphConnections<String> connections = new GraphConnections<String>()
                .addConnection("north", "south")
                .addConnection("east", "west")
                .addConnection("left", "right");

        assertThat(connections.isConnected("north", "left"), is(false));
        assertThat(connections.isConnected("south", "west"), is(false));
        assertThat(connections.isConnected("right", "north"), is(false));
    }

    @Test
    public void shouldReturnIfNodesAreDeeplyConnected() {
        GraphConnections<String> connections = new GraphConnections<String>()
                .addConnection("north", "south")
                .addConnection("north", "west")
                .addConnection("south", "right");

        assertThat(connections.isDeeplyConnected("north", "right"), is(true));
    }

    @Test
    public void shouldReturnIfNodesAreDeeplyConnectedBy2Levels() {
        GraphConnections<String> connections = new GraphConnections<String>()
                .addConnection("north", "south")
                .addConnection("south", "west")
                .addConnection("west", "right");

        assertThat(connections.isDeeplyConnected("north", "right"), is(true));
    }

    @Test
    public void shouldReturnFalseIfNodesAreNotDeeplyConnected() {
        GraphConnections<String> connections = new GraphConnections<String>()
                .addConnection("north", "south")
                .addConnection("north", "west")
                .addConnection("south", "right");

        assertThat(connections.isDeeplyConnected("south", "west"), is(false));
    }

    @Test
    public void shouldTraverseAndReturnDirectConnections() {
        GraphConnections<String> connections = new GraphConnections<String>()
                .addConnection("north", "south")
                .addConnection("north", "west")
                .addConnection("south", "right");
        List<String> path = connections.traverse("north", "south");
        assertThat(path, hasItems("north", "south"));
    }

    @Test
    public void shouldTraverseAndReturn1LevelDeep() {
        GraphConnections<String> connections = new GraphConnections<String>()
                .addConnection("north", "south")
                .addConnection("south", "west")
                .addConnection("west", "right");
        List<String> path = connections.traverse("north", "west");

        assertThat(path, hasItems("north", "south", "west"));
    }

    @Test
    public void shouldReturnTrueForDeeplyConnectedAcrossMultipleCandidates() {
        GraphConnections<String> connections = new GraphConnections<String>()
                .addConnection("north", "south")
                .addConnection("north", "west")
                .addConnection("west", "right");
        assertThat(connections.isDeeplyConnected("north", "right"), is(true));
    }


    @Test
    public void shouldTraverseAndReturn1LevelDeepAcrossCandidates() {
        GraphConnections<String> connections = new GraphConnections<String>()
                .addConnection("north", "south")
                .addConnection("north", "west")
                .addConnection("west", "right");
        List<String> path = connections.traverse("north", "right");
        assertThat(path, hasItems("north", "west", "right"));
    }

    @Test
    public void shouldReturnCandidatesForAGivenNode() {
        GraphConnections<String> connections = new GraphConnections<String>()
                .addConnection("north", "south")
                .addConnection("north", "west")
                .addConnection("north", "right");
        List<String> candidates = connections.candidates("north");
        assertThat(candidates.size(), is(3));
        assertThat(candidates, hasItems("south", "west", "right"));
    }


}