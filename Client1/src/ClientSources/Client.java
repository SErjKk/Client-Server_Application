package ClientSources;
import Collection.FuelType;
import Collection.Vehicle;
import Collection.VehicleType;
import Commands.*;
import Filters.*;
import Functional.Message;
import java.io.*;
import java.net.*;
import java.util.Properties;

public class Client {
    public Client() {}

    private DatagramSocket socket;
    private SocketAddress address;
    private ByteArrayOutputStream out;
    public static Properties properties;
    private String answer;

    //Настройка подключения
    public void connect() {
        try {
            int port = 59812;
            socket = new DatagramSocket();
            socket.setSoTimeout(1000);
            address = new InetSocketAddress(InetAddress.getLocalHost(), port);

        } catch (SocketException | UnknownHostException e) {
            System.out.println("{ошибка соединения: " + e.getMessage() + "}");
        }
    }

    //Методы
    public boolean authorization(String login, String password) throws IOException {
        Authorization command = new Authorization(login, password);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        String answer = deserialization(receiveBuffer);
        return answer.equals("1");
    }
    public boolean registration(String login, String password) throws IOException {
        Registration command = new Registration(login, password);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        String answer = deserialization(receiveBuffer);
        return answer.equals("1");
    }
    public boolean delete(String login, String password, int id) throws IOException {
        Delete command = new Delete(login, password, id);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        String answer = deserialization(receiveBuffer);
        return answer.equals("1");
    }
    public boolean update(String login, String password, Vehicle vehicle) throws IOException {
        Update command = new Update(login, password, vehicle);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        String answer = deserialization(receiveBuffer);
        return answer.equals("1");
    }
    public boolean add(String login, String password, Vehicle vehicle) throws IOException {
        Add command = new Add(login, password, vehicle);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        String answer = deserialization(receiveBuffer);
        return answer.equals("1");
    }
    public String show(String login, String password) throws IOException {
        Show command = new Show(login, password);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        return deserialization(receiveBuffer);
    }
    public String info(String login, String password) throws IOException {
        Info command = new Info(login, password);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        return deserialization(receiveBuffer);
    }
    public String help(String login, String password) throws IOException {
        Help command = new Help(login, password);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        return deserialization(receiveBuffer);
    }
    public String clear(String login, String password) throws IOException {
        Clear command = new Clear(login, password);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        return deserialization(receiveBuffer);
    }
    public String average(String login, String password) throws IOException {
        Average command = new Average(login, password);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        return deserialization(receiveBuffer);
    }

    //Фильтры
    public String filter_id(String login, String password, String condition, int id) throws IOException {
        IdFilter command = new IdFilter(login, password, condition, id);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        return deserialization(receiveBuffer);
    }
    public String filter_x(String login, String password, String condition, long x) throws IOException {
        xFilter command = new xFilter(login, password, condition, x);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        return deserialization(receiveBuffer);
    }
    public String filter_y(String login, String password, String condition, float y) throws IOException {
        yFilter command = new yFilter(login, password, condition, y);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        return deserialization(receiveBuffer);
    }
    public String filter_power(String login, String password, String condition, Long power) throws IOException {
        powerFilter command = new powerFilter(login, password, condition, power);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        return deserialization(receiveBuffer);
    }
    public String filter_capacity(String login, String password, String condition, double capacity) throws IOException {
        capacityFilter command = new capacityFilter(login, password, condition, capacity);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        return deserialization(receiveBuffer);
    }
    public String filter_type(String login, String password, VehicleType type) throws IOException {
        typeFilter command = new typeFilter(login, password, type);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        return deserialization(receiveBuffer);
    }
    public String filter_fuel(String login, String password, FuelType fuel) throws IOException {
        fuelFilter command = new fuelFilter(login, password, fuel);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        return deserialization(receiveBuffer);
    }
    public String filter_owner(String login, String password, String owner) throws IOException {
        ownerFilter command = new ownerFilter(login, password, owner);
        connect();

        byte[] sendBuffer = serialization(command);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[65000];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
            socket.receive(receivePacket);
        } catch (SocketTimeoutException e) {
            System.out.println("{сервер временно недоступен}");
        }

        return deserialization(receiveBuffer);
    }

    //Сериализация
    public byte[] serialization(Object command) {
        try {
            out = new ByteArrayOutputStream();
            ObjectOutputStream obj = new ObjectOutputStream(out);
            obj.writeObject(command);

        } catch (IOException e) {
            System.out.println("{ошибка при сериализации объекта: " + e.getMessage() + "}");
        }
        return out.toByteArray();
    }

    //Десериализация
    public String deserialization(byte[] receiveBuffer) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(receiveBuffer);
            ObjectInputStream obj = new ObjectInputStream(in);
            Message command = (Message) obj.readObject();
            answer = command.getStr();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("{ошибка при десериализации объекта: " + e.getMessage() + "}");
        }
        return answer;
    }

    public Properties getProperties() {
        return properties;
    }
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}